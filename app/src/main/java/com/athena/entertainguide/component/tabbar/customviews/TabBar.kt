package com.athena.entertainguide.component.tabbar.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.athena.entertainguide.databinding.TabBarBinding

/**
 * The TabBar component is a custom navigation view
 */
internal class TabBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = TabBarBinding.inflate(LayoutInflater.from(context), this, true)

    private val navigationItems: MutableList<TabBarItem> = mutableListOf()
    private val navigationItemViews: MutableList<TabBarItemView> = mutableListOf()
    private var selectedItemPosition: Int = -1
    private var onClickListener: ((TabBarItem) -> Unit) = {}

    fun setItems(items: List<TabBarItem>, firstSelectedItem: Int, recreated: Boolean) {
        navigationItemViews.clear()
        navigationItems.clear()
        navigationItems.addAll(items)
        rebuildTabBar()
        if (recreated.not()) {
            post { setSelectedItem(firstSelectedItem) }
        }
    }

    private fun rebuildTabBar() {
        binding.llTabBarContainer.removeAllViews()
        navigationItemViews.clear()

        val weight = DEFAULT_SCALE / navigationItems.size

        navigationItems.forEachIndexed { position, item ->
            val homeNavigationItem = TabBarItemView.createInstance(context, item.text, item.iconRes).apply {
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, weight)
                setOnClickListener { setSelectedItem(position) }
            }

            navigationItemViews.add(homeNavigationItem)
            binding.llTabBarContainer.addView(homeNavigationItem)
        }
    }

    fun setMenuClickListener(listener: (TabBarItem) -> Unit) {
        onClickListener = listener
    }

    fun setSelectedItem(position: Int) {
        // Avoid re-selecting the same item.
        if (selectedItemPosition == position) {
            return
        }
        onClickListener(navigationItems[position])
        selectItem(position)
    }

    private fun selectItem(position: Int) {
        // Deselect the previously selected item
        navigationItemViews.getOrNull(selectedItemPosition)?.isSelected = false

        // Select the new item
        navigationItemViews.getOrNull(position)?.isSelected = true
        selectedItemPosition = position
    }

    private companion object {
        const val DEFAULT_SCALE = 1f
    }
}