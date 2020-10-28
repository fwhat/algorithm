package golang

/**
 * 只有一种数出现了奇数次，其他数都是偶数次
 */
func FindOddTimesNumbers(arr []int) int {
	num := 0
	for _, v := range arr {
		num ^= v
	}

	return num
}
