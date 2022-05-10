package com.example.affirmations

import android.content.Context
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.model.Affirmation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AffirmationsAdapterTests {
    private val context = mock(Context::class.java)

    private val data = listOf(
        Affirmation(R.string.affirmation1, R.drawable.image1),
        Affirmation(R.string.affirmation2, R.drawable.image1),
        Affirmation(R.string.affirmation3, R.drawable.image3),
        Affirmation(R.string.affirmation4, R.drawable.image4),
        Affirmation(R.string.affirmation5, R.drawable.image5),
        Affirmation(R.string.affirmation6, R.drawable.image6),
        Affirmation(R.string.affirmation7, R.drawable.image7),
        Affirmation(R.string.affirmation8, R.drawable.image8),
        Affirmation(R.string.affirmation9, R.drawable.image9),
        Affirmation(R.string.affirmation10, R.drawable.image10)
    )

    private val adapter = ItemAdapter(context, data)

    @Test
    fun adapter_size() {
        assertEquals(
            "ItemAdapter is not the correct size",
            data.size,
            adapter.itemCount
        )
    }
}