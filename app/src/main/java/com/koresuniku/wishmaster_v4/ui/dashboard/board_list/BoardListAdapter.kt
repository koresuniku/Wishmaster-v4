package com.koresuniku.wishmaster_v4.ui.dashboard.board_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData

/**
 * Created by koresuniku on 12.11.17.
 */

class BoardListAdapter(private val mContext: Context, private val mBoardsLists: ArrayList<Pair<String, ArrayList<Pair<String, String>>>>)
    : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any = mBoardsLists[groupPosition].second

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var newConvertView = convertView
        if (newConvertView == null) {
            newConvertView = LayoutInflater.from(mContext).inflate(
                    R.layout.fragment_board_list_group_item, parent, false)
        }
        val groupName: TextView? = newConvertView?.findViewById(R.id.group_board_name)
        groupName?.text = mBoardsLists[groupPosition].first

        val indicator: ImageView? = newConvertView?.findViewById(R.id.group_item_indicator)
        if (isExpanded) {
            indicator?.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
        } else {
            indicator?.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
        }
        return newConvertView!!
    }

    override fun getChildrenCount(groupPosition: Int): Int = mBoardsLists[groupPosition].second.size

    override fun getChild(groupPosition: Int, childPosition: Int): Any = mBoardsLists[groupPosition].second[childPosition].second

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var newConvertView = convertView
        if (newConvertView == null) {
            newConvertView = LayoutInflater.from(mContext).inflate(
                    R.layout.fragment_board_list_child_item, parent, false)
        }
        val childName: TextView? = newConvertView?.findViewById(R.id.child_board_name)
        val name = "/${mBoardsLists[groupPosition].second[childPosition].first}/ " +
                "- ${mBoardsLists[groupPosition].second[childPosition].second}"
        childName?.text = name
        return newConvertView!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun getGroupCount(): Int = mBoardsLists.size
}