package golang

import "testing"

func TestLocalMinimum(t *testing.T) {
	println(LocalMinimum([]int{1, 2, 3, 4}))
	println(LocalMinimum([]int{2, 1, 4, 3}))

	println(LocalMinimum([]int{20, 10, 8, 4, 20, 30, 40}))
	println(LocalMinimum([]int{20, 10, 11, 10, 15, 30, 40}))
}

func TestLocalMinimum2(t *testing.T) {
	println(LocalMinimum2([]int{1, 2, 3, 4}))
	println(LocalMinimum2([]int{2, 1, 4, 3}))

	println(LocalMinimum2([]int{20, 10, 8, 4, 20, 30, 40}))
	println(LocalMinimum2([]int{20, 10, 11, 10, 15, 30, 40}))
}
