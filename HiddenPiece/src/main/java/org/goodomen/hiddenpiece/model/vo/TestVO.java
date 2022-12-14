package org.goodomen.hiddenpiece.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// getter, setter, toString, RequiredArgsConstructor를 하나로 집약해둔 Annotatation
@AllArgsConstructor	// 클래스의 모든 요소들을 가지고 있는 Constructor를 생성함(안보이는데 있음)
@NoArgsConstructor	// 클래스의 요소를 하나도 가지고있지 않은 기본 Constructor를 생성함(안보이는데 있음
@Builder	// 다른 클래스에서 이 클래스 객체를 생성할 때 new TestVO("java","a")이런식으로 생성하는게 아닌
			// TestVO testVO = TestVO.builder().id("java").password("a") 이런 방법으로 생성할 수 있다
			// 객체의 요소가 적을 경우에는 상관없으나 만일 요소가 여러 개일 경우 가독성이 떨어지며 요소값을 넣는 순서가 꼬일 수 있다.
			// 만일 TestVO의 요소가 id, password 두 개만 있는것이 아니라 10개의 요소가 있다고 가정할 경우
			// new TestVO()를 통해 생성시 10개의 요소를 전부 순서에 맞춰 넣어야한다
			// 그러나 Builder를 사용할 경우 .id().password().address()등으로 괄호 안에 해당되는 값을 넣으면 된다.
			// 즉 가독성이 훨씬 좋아지며 new TestVO()와 달리 순서에 영향을 받지 않는다 => .address("오리").password("a").id("java") ==  .id("java").password("a").address("오리")
public class TestVO {
	private String id;
	private String password;
}
