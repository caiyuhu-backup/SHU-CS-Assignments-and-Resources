//该文档用于接口的统一管理

import requests from "@/api/request"
// import mockRequests from "@/api/mockRequest"

//获取验证码
export const reqGetCode = (email)=>requests({
    url: `/ec/send/${email}`,
    method: 'get'
})

//校验验证码是否正确
export const reqCheckCode = (code, eid)=>requests({
    url: `/ec/check/${code}/${eid}`,
    method: 'get'
})

//注册用户
export const reqRegister = (data)=>requests({
    url: '/user/register',
    method: 'post',
    data
})

//用户登录
export const reqLogin = (data)=>requests({
    url: '/user/login',
    method: 'post',
    data
})

//用户登录后在首页获取用户信息
export const reqGetUserInfo = ()=>requests({
    url: `/user/userInfo`,
    method: 'post'
})

// 获取学校列表
export const reqGetSchoolList = (data)=>requests({
    url: '/school/list',
    method: 'post',
    data
})

// 搜索学校
export const reqSearchSchool = (data)=>requests({
    url: '/school/query',
    method: 'post',
    data
})

// 获取某一学校具体信息
export const reqGetSchoolDetailBySid = (sid)=>requests({
    url: `/school/detail/${sid}`,
    method: 'get'
})

// 获取老师列表
export const reqGetTeacherList = (sid, currentPage)=>requests({
    url: `/teacher/briefInfo/${sid}/${currentPage}`,
    method: 'get'
})

// 老师具体信息
export const reqGetTeacherDetail = (tid)=>requests({
    url: `/teacher/detailedInfo/${tid}`,
    method: 'get'
})

// 搜索老师列表
export const reqSearchTeacher = (data)=>requests({
    url: '/teacher/query',
    method: 'post',
    data
})

// 查询文章
export const reqGetArticleById = (id)=>requests({
    url: `/article/${id}`,
    method: 'get'
})

// 查询文章
export const reqSearchArticle = (data)=>requests({
    url: '/article/query',
    method: 'post',
    data
})

// 查询用户对该文章的点赞、收藏状态
export const reqGetAUCondition = (data)=>requests({
    url: '/article/condition',
    method: 'post',
    data
})

// 查询评论
export const reqGetCommentById = (data)=>requests({
    url: '/comment/get',
    method: 'post',
    data
})

// 添加父评论
export const reqAddParentComment = (data)=>requests({
    url: '/comment/add',
    method: 'post',
    data
})

// 给评论点赞
export const reqAddCommentLike = (data)=>requests({
    url: '/comment/addLike',
    method: 'post',
    data
})

// 取消评论点赞
export const reqRevokeCommentLike = (data)=>requests({
    url: '/comment/revokeLike',
    method: 'post',
    data
})

// 发帖
export const reqSaveArticle = (data)=>requests({
    url: '/article/add',
    method: 'post',
    data
})

// 通过tag_id获取文章
export const reqGetArticleByTid = (tid)=>requests({
    url: `/tag/article/${tid}`,
    method: 'get'
})

// 获取搜索记录
export const reqGetSearchHistory = (uid)=>requests({
    url: `/search/list/${uid}`,
    method: 'get',
})

// 获取schoolNameList
export const reqGetSchoolNameList = ()=>requests({
    url: '/school/schoolNameList',
    method: 'get'
})

// 获取tagNameList
export const reqGetTagNameList = ()=>requests({
    url: '/tag/list',
    method: 'get'
})

// 增加浏览量
export const reqAddHistory = (data)=>requests({
    url: '/article/addHistory',
    method: 'post',
    data
})

// 增加点赞
export const addLikes = (data)=>requests({
    url: '/article/addLikes',
    method: 'post',
    data
})

// 取消点赞
export const revokeLikes = (data)=>requests({
    url: '/article/revokeLikes',
    method: 'post',
    data
})

// 增加收藏
export const addFavorite = (data)=>requests({
    url: '/article/addFavorite',
    method: 'post',
    data
})

// 取消收藏
export const revokeFavorite = (data)=>requests({
    url: '/article/revokeFavorite',
    method: 'post',
    data
})

// 增加浏览量
export const addRepost = (data)=>requests({
    url: '/article/addRepost',
    method: 'post',
    data
})

// 上传图片
export const reqUploadImage = (data)=>requests({
    url: '/upload/image',
    method: 'post',
    data,
    headers: { 'Content-Type': 'multipart/form-data' },
})

// 添加新的标签
export const reqAddNewTag = (data)=>requests({
    url: '/tag/addTagName',
    method: 'post',
    data
})

// 获取招考信息
export const reqGetRecruitInfo = (data)=>requests({
    url: '/article/getRecruitInfo',
    method: 'post',
    data
})

// 获取真题
export const reqGetExamQuestion = (data)=>requests({
    url: '/File/getFiles',
    method: 'post',
    data
})

// 获取所有分数线
export const reqGetScoreLineList = (data)=>requests({
    url: '/scoreline/page',
    method: 'post',
    data
})

// 获取所有分数线
export const reqGetScoreLineBySid = (sid)=>requests({
    url: `/scoreline/school/${sid}`,
    method: 'get'
})
