/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

package team.duckie.quackquack.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList

private val QuackGridHorizontalPadding = 8.dp
private val QuackVerticalSpacePadding = 24.dp
private val QuackHorizontalSpacePadding = 10.dp


/**
 * [QuackSimpleGridLayout] 을 구현합니다.
 *
 * @param columns Grid가 몇 줄로 구성되어있는지에 대한 값
 * @param items List로 사용할 아이템 데이터
 * @param verticalSpace GridLayout 의 아이템간의 수직 간격
 * @param horizontalSpace GridLayout 의 아이템간의 수평 간격
 * @param horizontalPadding GridLayout 의 레이아웃 자체의 내부 패딩
 * @param itemContent 구현할 아이템의 Composable 함수
 *
 */
@Composable
public fun <T> QuackSimpleGridLayout(
    columns: Int,
    items: PersistentList<T>,
    verticalSpace: Dp = QuackVerticalSpacePadding,
    horizontalSpace: Dp = QuackHorizontalSpacePadding,
    horizontalPadding: Dp = QuackGridHorizontalPadding,
    itemContent: @Composable (Int, T) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            paddingValues = PaddingValues(
                horizontal = horizontalPadding,
            )
        ),
        columns = GridCells.Fixed(
            count = columns
        ),
        content = {
            itemsIndexed(
                items = items,
            ) { index, item ->
                QuackSimpleGridItem(
                    verticalSpace = verticalSpace,
                    horizontalSpace = horizontalSpace,
                    itemContent = {
                        itemContent(
                            index,
                            item,
                        )
                    },
                    index = index,
                    size = items.size,
                )
            }
        },
    )
}
//
///**
// * QuackHeaderGridLayout 을 구현합니다.
// *
// * GridLayout 중에 첫 아이템만 다른 아이템으로 사용해야하는 UI에 사용할 수 있습니다.
// *
// * @param columns
// * @param items
// * @param verticalSpace
// * @param horizontalSpace
// * @param horizontalPadding
// * @param itemContent
// * @param header
// */
//
//@Composable
//fun <T> QuackHeaderGridLayout(
//    columns: Int,
//    items: PersistentList<T>,
//    verticalSpace: Dp,
//    horizontalSpace: Dp,
//    horizontalPadding: Dp = QuackGridHorizontalPadding,
//    itemContent: @Composable (Int, T) -> Unit,
//    header: @Composable () -> Unit,
//) {
//
//    val gridItems = listOf(items[0]) + items
//
//    LazyVerticalGrid(
//        modifier = Modifier.padding(
//            paddingValues = PaddingValues(
//                horizontal = horizontalPadding,
//            )
//        ),
//        columns = GridCells.Fixed(
//            count = columns
//        ),
//        content = {
//            itemsIndexed(
//                items = gridItems,
//            ) { index, item ->
//                QuackSimpleGridItem(
//                    verticalSpace = verticalSpace,
//                    horizontalSpace = horizontalSpace,
//                    itemContent = {
//                        itemContent(
//                            index,
//                            item,
//                        )
//                    },
//                    header = header,
//                    index = index,
//                    size = gridItems.size,
//                )
//            }
//        },
//    )
//}

/**
 * [QuackSimpleGridItem] 를 구현합니다
 *
 * @param index 현재 아이템의 index
 * @param size 아이템의 갯수
 * @param verticalSpace 아이템간의 수직 간격
 * @param horizontalSpace 아이템간의 수평 간격
 * @param itemContent 일반적인 GridLayout 에서 사용되는 item @Composable 함수
 * @param footer 마지막 아이템으로 올 수 있는 @Composable 함수
 * @param header 첫 번째 아이템으로 올 수 있는 @Composable 함수
 */

@Composable
private fun QuackSimpleGridItem(
    index: Int,
    size: Int,
    verticalSpace: Dp,
    horizontalSpace: Dp,
    itemContent: @Composable () -> Unit,
    footer: (@Composable () -> Unit)? = null,
    header: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = Modifier.padding(
            paddingValues = PaddingValues(
                horizontal = horizontalSpace,
                vertical = verticalSpace,
            )
        ),
    ) {
        if ( index == 0 && header != null){
            header()
        }
        else if ( index == size && footer != null){
            footer()
        }
        else {
            itemContent()
        }
    }
}
