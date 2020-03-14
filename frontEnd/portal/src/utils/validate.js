/**
 * 判断字符串是否为空
 * @param {string} str
 * @returns {Boolean}
 */
export function validateString (str) {
  return (typeof str === 'undefined' || str == null || str.trim() !== '')
}
