package golang

/**
 * golang 中 ^ 在一元运算符代表 取反 (~)
 * 				二元运算符代表 异或
 */
func FindTheLeftmost1InANumber(a int) int {
	return a & (^a + 1)
}
