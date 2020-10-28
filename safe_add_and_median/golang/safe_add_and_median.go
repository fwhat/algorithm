package golang

/**
 * b >= a
 */
func SafeAddAndMedian(a, b int) int {
	return a + ((b - a) >> 1)
}
