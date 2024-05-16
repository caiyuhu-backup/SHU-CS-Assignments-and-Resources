// 此处第二个参数vm，就是我们在页面使用的this，你可以通过vm获取vuex等操作
// https://uviewui.com/js/http.html#%E4%BD%95%E8%B0%93%E8%AF%B7%E6%B1%82%E6%8B%A6%E6%88%AA%EF%BC%9F
const install = (Vue, vm) => {
	// 此处没有使用传入的params参数
	// user相关	
	const sendCode = (phone) => vm.$u.post('user/code?phone=' + phone)
	
	const login = (params) => vm.$u.post('user/login', params)
	
	const getUserInfo = () => vm.$u.post('user/info')
	
	const logout = () => vm.$u.get('user/logout')
	
	// vendor相关
	const getVendorBriefInfo = (vendorId) => vm.$u.get(`vendor/briefInfo/${vendorId}`)
	
	const getCommentList = (params) => vm.$u.post('vendor/commentList', params)
	
	const updateVendorInfo = (params) => vm.$u.post('vendor/updateVendorInfo', params)
	
	const getCategoryList = (vendorId) => vm.$u.get('vendor/category/' + vendorId)
	
	// order相关
	const queryByVendorId = (params) => vm.$u.post('order/queryByVendorId', params)
	
	const checkQrCode = (orderId) => vm.$u.post('order/writeOff/' + orderId)
	
	// Product相关
	const updateProduct = (params) => vm.$u.post('vendor/updateProduct', params)
	
	const addProduct = (params) => vm.$u.post('vendor/addProduct', params)
	
	// 将各个定义的接口名称，统一放进对象挂载到vm.$u.api(因为vm就是this，也即this.$u.api)下
	vm.$u.api = {sendCode, login, getUserInfo, getVendorBriefInfo, logout, queryByVendorId, checkQrCode, updateVendorInfo, updateProduct, 
	getCategoryList, addProduct, }
}

export default {
	install
}