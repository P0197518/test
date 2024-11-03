/**
 * 保存数据到localStorage
 * @param name 数据的名称
 * @param value 数据的值
 */
function storage(name, value) {
    localStorage.setItem(name, value);
}

/**
 * localStorage根据name获取value
 * @param name 数据的名称
 */
function getStorage(name) {
    return localStorage.getItem(name);
}

/**
 * 从localStorage中清空数据
 * @param name 数据的名称
 */
function clear(name) {
    localStorage.removeItem(name);
}