package golang

/**
 * 返回数组中局部最小位置(数组中可以有多个局部最小)
 * 条件1: 相邻数不相等
 * 局部最小位置定义：
 * 		定义1: 假设 [0, N-1] 0 位置的数比 1位置的数小，那0位置就是局部最小位置
 * 		定义2: 假设 [0, N-1] N-1 位置的数比 N-2位置的数小，那N-1位置就是局部最小位置
 * 		定义3: 假设 [0, N-1] 任意位置 i 其中 arr[i-1] > arr[i] < arr[i + 1] 那 i 位置就是局部最小位置
 */
func LocalMinimum(arr []int) int {
	size := len(arr)
	if size < 2 || arr == nil {
		return -1
	}

	if arr[0] < arr[1] {
		return arr[0]
	}

	if arr[size-1] < arr[size-2] {
		return size
	}

	l := 0
	r := size - 1

	for l <= r {
		mid := (r + l) / 2

		if arr[mid-1] > arr[mid] && arr[mid] < arr[mid+1] {
			return mid
		}

		if arr[mid-1] > arr[mid] {
			l = mid + 1
		}

		if arr[mid] < arr[mid+1] {
			r = mid - 1
			continue
		}
	}

	return -1
}

func LocalMinimum2(arr []int) int {
	size := len(arr)
	if size < 2 || arr == nil {
		return -1
	}

	if arr[0] < arr[1] {
		return arr[0]
	}

	if arr[size-1] < arr[size-2] {
		return size
	}

	l := 0
	r := size - 1

	for l <= r {
		mid := (r + l) / 2

		if arr[mid-1] < arr[mid] {
			r = mid - 1
		} else if arr[mid] > arr[mid+1] {
			l = mid + 1
		} else {
			return mid
		}
	}

	return -1
}
