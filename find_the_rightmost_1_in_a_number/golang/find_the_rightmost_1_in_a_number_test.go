package golang

import (
	"testing"
)

func TestFindTheLeftmost1InANumber(t *testing.T) {
	// 11111001000100111100
	// fmt.Printf("%b", 1020220)

	// answer: 100 -> 4
	println(FindTheLeftmost1InANumber(1020220))
}
