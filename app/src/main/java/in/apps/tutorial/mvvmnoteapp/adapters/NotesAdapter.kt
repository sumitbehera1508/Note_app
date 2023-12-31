package `in`.apps.tutorial.mvvmnoteapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import `in`.apps.tutorial.mvvmnoteapp.R
import `in`.apps.tutorial.mvvmnoteapp.models.Notes
import kotlin.random.Random

class NotesAdapter(private val context:Context , val listener: NotesAdapter.NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val notesList = ArrayList<Notes>()
    private val fullList = ArrayList<Notes>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.single_item,parent,false)

        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.titles.text = currentNote.title
        holder.titles.isSelected = true

        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes.text = currentNote.note

        holder.notes_layout.setCardBackgroundColor(randomColorGenerator())

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onItemLongClicked(notesList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }



    private fun randomColorGenerator() :Int{
        val list = arrayListOf<Int>()
        list.add(R.color.n1)
        list.add(R.color.n2)
        list.add(R.color.n3)
        list.add(R.color.n4)
        list.add(R.color.n5)
        list.add(R.color.n6)
        list.add(R.color.n7)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    fun updateList(newsList : List<Notes>){
        fullList.clear()
        fullList.addAll(newsList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search:String){
        notesList.clear()
        for (item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){
                notesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val notes_layout = itemView.findViewById<CardView>(R.id.single_layout)
        val titles = itemView.findViewById<TextView>(R.id.title_textView)
        val notes = itemView.findViewById<TextView>(R.id.note_textView)
        val date = itemView.findViewById<TextView>(R.id.date_textView)
    }

    interface NotesClickListener {
        fun onItemClicked(notes: Notes)
        fun onItemLongClicked(notes: Notes,cardView: CardView)
    }

}