package golang

import "testing"

func TestBinarySearch(t *testing.T) {
	println(BinarySearch([]int{100, 111, 333, 5555, 66666, 21111323}, 5555))
	println(BinarySearch([]int{100, 111, 333, 5555, 21111323}, 21111323))
	println(BinarySearch([]int{100, 111, 333, 5555, 21111323}, 1122))
}
