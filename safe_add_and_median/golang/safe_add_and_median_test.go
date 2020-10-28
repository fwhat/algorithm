package golang

import "testing"

func TestSafeAddAndMedian(t *testing.T) {
	println(SafeAddAndMedian(100, 200))
	println(SafeAddAndMedian(9223372036854775807, 9223372036854775807))

	a := 9223372036854775807
	b := 9223372036854775807

	println((a + b) / 2)
}
