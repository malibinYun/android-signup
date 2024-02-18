package nextstep.signup.study

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Rule
import org.junit.Test

class RecompositionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val username = mutableStateOf("")
    val otherValue = mutableStateOf(false)

    var count = 0

    @Composable
    fun UsernameTextField(
        username: String,
        other: Boolean,
    ) {
//        val isError = (username.length !in 2..5).also { count++ }

        val isError = remember(username) {
            (username.length !in 2..5).also { count++ }
        }

        TextField(
            value = username,
            onValueChange = {},
            isError = isError,
        )
    }

    // 리컴포지션 트리거를 만들어야함
    // 유효성 검사 로직이 불리는지 카운트 해줘야함.
    @Test
    fun 리컴포지션_될_때마다_유효성_검사한다() {
        // given
        composeTestRule.setContent {
            UsernameTextField(username = username.value, other = otherValue.value)
        }

        // when
        count = 0
        username.value = "김컴포즈"
//        otherValue.value = true

        // then

        composeTestRule
            .onNodeWithText("김컴포즈")
            .assertExists()
// 컴포즈 ui test는 바뀌었는지 인지하려면 시간확보를 해야함. 너무빨라서 바뀌었음에도 인지를 못함 ㅋㅋ 앰병
// 위 세줄 없으면 테스트 fail남 ㅋㅋ
        // ui가 다 갱신될 때 까지 기다려주는 로직임.

        assert(count == 1)


        //================

        otherValue.value = true
        composeTestRule
            .onNodeWithText("김컴포즈")
            .assertExists()
        assert(count == 2) // 1을 넣으면 test fail임
    }

    @Test
    fun 특정_값이_변경될때만_유효성_검사한다() {
        // given
        composeTestRule.setContent {
            UsernameTextField(username = username.value, other = otherValue.value)
        }

        // when
        count = 0
        username.value = "김컴포즈"
//        otherValue.value = true

        // then

        composeTestRule
            .onNodeWithText("김컴포즈")
            .assertExists()
// 컴포즈 ui test는 바뀌었는지 인지하려면 시간확보를 해야함. 너무빨라서 바뀌었음에도 인지를 못함 ㅋㅋ 앰병
// 위 세줄 없으면 테스트 fail남 ㅋㅋ
        // ui가 다 갱신될 때 까지 기다려주는 로직임.

        assert(count == 1)


        //================

        otherValue.value = true
        composeTestRule
            .onNodeWithText("김컴포즈")
            .assertExists()
        assert(count == 1) // 1을 넣으면 test fail임

//        composeTestRule.waitForIdle() // 뷰가 Idle상태가 될 때까지 기다리는 함수임
    }
}


// 트리의 각 노드를 병합하거나 생략할 수 있다고 함.
// 텍스트를 여러개 쓸 때도 하나로 병합되어버리는 경우가 있음.
// 그래서 테스트 환경에서 노드를 찾지 못하는 경우가 생길 수도 있음.
// 이런 경우에 onNodeWithText에 unMergedTree=true로 주면 됨.
// https://developer.android.com/jetpack/compose/testing#unmerged
// composeTestRule.onRoot().printToLog("TAG") 이 함수 호출로 아래 처럼 노드 형태를 좀 엿볼 수 있음
// Node #1 at (...)px
// |-Node #2 at (...)px
//   Role = 'Button'
//   Text = '[Hello, World]'
//   Actions = [OnClick, GetTextLayoutResult]
//   MergeDescendants = 'true'
