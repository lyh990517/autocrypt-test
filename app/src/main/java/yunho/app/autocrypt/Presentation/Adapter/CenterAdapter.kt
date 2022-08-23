package yunho.app.autocrypt.Presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.databinding.ItemCenterBinding

class CenterAdapter : RecyclerView.Adapter<CenterAdapter.ViewHolder>() {
    private var CenterList = listOf<CenterEntity>()
    private lateinit var ItemListener: (CenterEntity) -> Unit

    inner class ViewHolder(
        private val binding: ItemCenterBinding,
        private val Listener: (CenterEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(centerEntity: CenterEntity) = with(binding) {
            centerName.text = centerEntity.centerName

            root.setOnClickListener {
                Listener(centerEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterAdapter.ViewHolder {
        val view = ItemCenterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, ItemListener)
    }

    override fun onBindViewHolder(holder: CenterAdapter.ViewHolder, position: Int) {
        holder.bindData(CenterList[position])
    }

    override fun getItemCount(): Int = CenterList.size

    fun setData(list: List<CenterEntity>, listener: (CenterEntity) -> Unit) {
        CenterList = list
        ItemListener = listener
        notifyDataSetChanged()
    }
}