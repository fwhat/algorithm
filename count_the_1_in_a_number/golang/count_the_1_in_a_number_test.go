package golang

import (
	"fmt"
	"testing"
)

func TestCountThe1InANumber(t *testing.T) {

	// 11101000011001111000011111000 -> 15
	fmt.Printf("%b\n", 487387384)

	println(CountThe1InANumber(487387384))
}
