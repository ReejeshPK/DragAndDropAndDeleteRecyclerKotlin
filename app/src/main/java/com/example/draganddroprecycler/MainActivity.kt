package com.example.draganddroprecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnStartDragListener {

    /**

     References:
     Drag and drop:
     https://androidwave.com/drag-and-drop-recyclerview-item-android/

     Delete:
     https://stackoverflow.com/questions/49827752/how-to-implement-drag-and-drop-and-swipe-to-delete-in-recyclerview
     *
     * */

    lateinit var adapter: DragDropRecyclerAdapter
    lateinit var touchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter= DragDropRecyclerAdapter(this)
        populateListItem()

        /**Start of drag and drop*/
        val callback: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        /***End of drag and drop*/


        /**Start swipe to delte code*/
        val itemTouchHelperCallback =
                object :
                        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                    ): Boolean {

                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        //noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.adapterPosition))
                        adapter.removeThis(viewHolder.adapterPosition)
                        Toast.makeText(
                                this@MainActivity,
                                "Deleted",
                                Toast.LENGTH_SHORT
                        ).show()
                    }

                }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        /**end of swipe to delte code*/


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }

    private fun populateListItem() {
        val users = listOf(
                "Anuj",
                "Bhanu",
                "Chetan",
                "Devendra",
                "Esha",
                "Farmod",
                "Ganesh",
                "Hemant",
                "Ishaan",
                "Jack",
                "Kamal",
                "Lalit",
                "Mona"
        )
        adapter.setUsers(users)
    }
}
