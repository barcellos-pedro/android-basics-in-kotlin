/**
* Program that implements classes for different kinds of dwellings.
* Shows how to:
* Create class hierarchy, variables and functions with inheritance,
* abstract class, overriding, and private vs. public variables.
*/

import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
    val cabin = SquareCabin(6, 50.0)
    val hut = RoundHut(3, 10.0)
    val tower = RoundTower(4, 15.5)
    
    println(cabin)
    println("Floor Area: ${cabin.floorArea()}")
    
    println(hut)
    hut.getRoom()
    println(hut)
    println("Floor Area: ${hut.floorArea()}")
    println("Carpet size: ${hut.calculateMaxCarpetSize()}")
    
    println(tower)
    println("Floor Area: ${tower.floorArea()}")
    println("Carpet size: ${tower.calculateMaxCarpetSize()}")
}

abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int
    
    abstract fun floorArea(): Double
    
    fun getRoom() {
        if (residents < capacity) {
            residents++
            println("You got a room!")
        } else {
        	println("Sorry, at capacity and no rooms left.")
        }
    }
    
    fun hasRoom(): Boolean {
        return residents < capacity
    }
    
    override fun toString(): String {
        with(this) {
            return "===============================\n" +
        			"Material: ${buildingMaterial}\n" +
        			"Capacity: ${capacity}\n" +
	        		"Has room? ${hasRoom()}"
    	}
    }
}

class SquareCabin(
    residents: Int,
    val length: Double
): Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6
    
    override fun floorArea(): Double {
        return length * length
    }
}

open class RoundHut(
    residents: Int,
    val radius: Double
): Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4
    
    override fun floorArea(): Double {
        return PI * radius * radius
    }
    
    fun calculateMaxCarpetSize() : Double {
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }
}

class RoundTower(
    residents: Int,
    radius: Double,
    val floors: Int = 2
): RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
    
    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}
