package golang

import (
	"fmt"
	"testing"
)

func TestSwapWithoutTemp(t *testing.T) {
	a := 100
	b := 102

	SwapWithoutTemp(&a, &b)

	fmt.Println(a, b)
}
