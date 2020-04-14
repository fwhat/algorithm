package _445

import "testing"

func TestAddTwoNumbers(t *testing.T) {
	numbers := addTwoNumbers(&ListNode{
		Val: 2,
		Next: &ListNode{
			Val: 4,
			Next: &ListNode{
				Val:  3,
				Next: nil,
			},
		},
	},
		&ListNode{
			Val: 5,
			Next: &ListNode{
				Val: 6,
				Next: &ListNode{
					Val:  4,
					Next: nil,
				},
			},
		},
	)
	println(numbers)

	res := []int{8, 0, 7}

	i := 0

	if res[i] != numbers.Val {
		t.Error("invalid")
	}
	for numbers.Next != nil {
		numbers = numbers.Next
		i++
		if res[i] != numbers.Val {
			t.Error("invalid")
		}
	}

	for i != 2 {
		t.Error("invalid")
	}
}
