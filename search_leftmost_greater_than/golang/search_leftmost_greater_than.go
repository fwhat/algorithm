package golang

func SearchLeftmostGreaterEqualThan(arr []int, than int) int {
	most := -1
	min := 0
	max := len(arr) - 1
	for min <= max {
		mid := (max + min) / 2
		if arr[mid] >= than {
			most = mid
			max = mid - 1
		} else {
			min = mid + 1
		}
	}

	return most
}
