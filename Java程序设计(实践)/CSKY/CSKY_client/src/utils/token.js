export const setToken = (token) => {
	localStorage.setItem('TOKEN', token)
}

export const getToken = () => {
	return localStorage.getItem('TOKEN')
}

export const rmToken = () => {
	localStorage.removeItem('TOKEN')
}
