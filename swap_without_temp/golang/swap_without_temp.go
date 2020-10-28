package golang

func SwapWithoutTemp(a, b *int) {
	*a = *a ^ *b
	*b = *a ^ *b
	*a = *a ^ *b
}

/**
 * a != b
 */
func SwapWithoutTempArr(arr []int, a, b int) {
	if a == b {
		return
	}

	arr[a] = arr[a] ^ arr[b]
	arr[b] = arr[a] ^ arr[b]
	arr[a] = arr[a] ^ arr[b]
}
