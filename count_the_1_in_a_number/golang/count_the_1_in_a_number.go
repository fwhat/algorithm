package golang

func CountThe1InANumber(a int) int {
	count := 0
	for a != 0 {
		if a == -1 {
			panic(a)
		}
		rightOne := a & (^a + 1)
		count++
		a ^= rightOne
	}

	return count
}
