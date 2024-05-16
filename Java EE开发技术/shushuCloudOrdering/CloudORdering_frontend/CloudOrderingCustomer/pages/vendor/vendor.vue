<template>
	<view>
		<u-navbar :background="navbarBackground" :height="85"
			:is-fixed="true" back-icon-color="#ff9900" back-text="返回">
		</u-navbar>
		<view class="u-wrap">
			<view class="u-m-l-18 u-m-r-18">
				<text class="canteen-title">{{vendorInfo.vendorName}}</text>
				<view class="u-flex u-m-t-15">
					<view class="canteen-score"><u-icon name="star-fill"></u-icon>{{vendorInfo.score}}</view>
					<view class="u-font-25 u-content-color u-m-l-12">已售<u-count-to :start-val="0" :end-val="vendorInfo.totalSold" :font-size="25"></u-count-to>单</view>
					<text class="u-font-25 u-content-color u-m-l-12">￥{{vendorInfo.averagePrice}}/人</text>
				</view>
				<u-collapse class="u-m-b-20" ref="collapse">
					<u-collapse-item title="公告" :open="true">
						{{vendorInfo.announcement}}
					</u-collapse-item>
				</u-collapse>
				<u-subsection :list="optionList" :current="currentTab" active-color="#f29100" @change="changeTab" class="u-m-b-20"></u-subsection>
			</view>
			<view class="u-menu-wrap" v-show="currentTab == 0">
				<scroll-view scroll-y scroll-with-animation class="u-tab-view menu-scroll-view" :scroll-top="scrollTop"
				 :scroll-into-view="itemId">
					<view v-for="(item,index) in tabbar" :key="index" class="u-tab-item" :class="[currentOption == index ? 'u-tab-item-active' : '']"
					 @tap.stop="swichMenu(index)">
						<text class="u-line-1">{{item.categoryName}}</text>
					</view>
				</scroll-view>
				<scroll-view :scroll-top="scrollRightTop" scroll-y scroll-with-animation class="right-box" @scroll="rightScroll">
					<view class="page-view">
						<view class="class-item" :id="'item' + index" v-for="(item , index) in tabbar" :key="index">
							<view class="item-title">
								<text>{{item.categoryName}}</text>
							</view>
							<view class="item-container">
								<view class="thumb-box" v-for="(item1, index1) in item.productVendorVoList" :key="index1" v-show="item1.productStatus === 1">
									<image class="item-menu-image" :src="item1.productImg"></image>
									<view>
										<view class="item-menu-name">
											{{item1.productName}}
										</view>
										<view class="item-menu-sale">
											<text class="u-m-r-28">销量: {{item1.soldNum}}</text>
											<text>好评: 100%</text>
										</view>
										<view class="item-menu-price">
											￥{{item1.sellPrice}}
											<image src="../../static/minus.png" class="item-menu-plus" v-show="item1.sale != 0" @click="minusOne(item1)"></image>
											<text style="color: #606266;" v-show="item1.sale != 0">{{item1.sale}}</text>
											<image src="../../static/plus.png" class="item-menu-plus" 
												@click="plusOne(item1)" v-show="vendorInfo.inBusiness === 1"></image>
										</view>
									</view>
								</view>
							</view>
						</view>
					</view>
				</scroll-view>
			</view>
			<view v-show="currentTab == 1" class="comment">
				<view v-for="(comment, index1) in commentList" :key="index1" class="comment-card">
					<view class="u-flex">
						<u-avatar :src="comment.avatar" class='u-m-r-20 u-m-l-20'></u-avatar>
						<view class="">
							<view style="color: #606266; font-weight: bold;" class="u-m-b-10">{{comment.userName}}</view>
							<view style="color: #909399;">{{comment.commentTime}}</view>
						</view>	
					</view>
					<view class="u-m-l-20 u-m-b-20">
						<u-rate :current="comment.score" :disabled="true" class='u-m-t-20 u-m-b-20'></u-rate>
						<view>{{comment.comment}}</view>
					</view>
					<view class="u-m-l-20 u-m-b-20 u-m-r-20">
						<u-swiper v-if="comment.imgList" :list="comment.imgList" :effect3d="true" name='' 
						img-mode='aspectFit' mode='number' height='320'></u-swiper>
					</view>
					<u-divider>
						<u-icon name='tags'></u-icon>
					</u-divider>
				</view>
				<u-loadmore :status="loadStatus" @loadmore='loadmoreComment'/>
			</view>
		</view>
		<view class="navigation" v-show="currentTab == 0" :style="navStyle">
			<view class="left">
				<view class="item car">
					<u-badge class="car-num" :count="totalCount" type="error" :offset="[-3, -6]"></u-badge>
					<image src="../../static/pot.png" style="width: 65rpx; height: 65rpx;"></image>
				</view>
				<view style="color: #dd6161;">
					<text style="font-size: 40rpx;">￥</text>
					<u-count-to :start-val="0" :end-val="totalPrice" :decimals="2" separator="," 
						:duration="600" :bold="true" font-size="40"></u-count-to>
				</view>
			</view>
			<view class="right">
				<view class="buy btn u-line-1" @click="toSubmitOrderPage">立即结算</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				scrollTop: 0, //tab标题的滚动条位置
				oldScrollTop: 0,
				currentOption: 0, // 预设当前项的值
				menuHeight: 0, // 左边菜单的高度
				menuItemHeight: 0, // 左边菜单item的高度
				itemId: '', // 栏目右边scroll-view用于滚动的id
				menuItemPos: [],
				arr: [],
				scrollRightTop: 0, // 右边栏目scroll-view的滚动条高度
				timer: null, // 定时器
				optionList: [{
					name: '点餐'
				}, {
					name: '评价'
				}],
				currentTab: 0,
				// 下面...
				navbarBackground: {
					background: '',
					backgroundSize: 'cover'
				},
				vendorInfo: {},
				// 选中的商品
				itemList: [],
				// 商铺id
				vendorId: 0,
				// 商品列表
				tabbar: [],
				// 评论区
				commentList: [],
				// 评论页
				commentPage: 1,
				loadStatus: 'loadmore'
			}
		},
		onLoad(options) {
			console.log(options.cid)
			this.vendorId = options.cid
			this.getVendorInfo()
		},
		computed: {
			// 商品总价
			totalPrice() {
				let sum = 0.0
				this.itemList.forEach(item=>{
					sum += item.sellPrice * item.sale
				})
				return sum
			},
			// 商品数量
			totalCount() {
				let sum = 0
				this.itemList.forEach(item=>{
					sum += item.sale
				})
				return sum
			},
			navStyle() {
				if (this.totalCount == 0)
					return {
						"opacity": "0.7",
						"filter": "alpha(opacity=60)",
						"backgroundColor": "#efefef"
					}
				return {}
			}
		},
		onReady() {
			this.getMenuItemTop()
		},
		onPullDownRefresh() {
			this.getVendorInfo()
			setTimeout(function () {
				uni.stopPullDownRefresh()
			}, 800);
		},
		onReachBottom() {
			if (this.currentTab === 1) {
				this.loadStatus = 'loading'
				this.commentPage += 1
				this.getCommentList()
				this.loadStatus = 'loadmore'
			}
		},
		methods: {
			loadmoreComment() {
				console.log('11111111111111111111');
				this.commentPage += 1
				this.getCommentList()
				this.loadStatus = 'loadmore'
			},
			// 获取评论列表
			async getCommentList() {
				const result = await this.$u.api.getCommentList({
					vendorId: this.vendorId,
					page: {
						current: this.commentPage,
						size: 5
					}
				})
				let prelength = this.commentList.length
				this.commentList = this.commentList.concat(result.commentList)
				let curlength = this.commentList.length
				if (prelength === curlength) {
					this.loadStatus = 'nomore'
				}
				console.log(result)
			},
			// 获取vendor信息
			async getVendorInfo() {
				const result = await this.$u.api.getVendorBriefInfo(this.vendorId)
				this.vendorInfo = result
				console.log('vendorInfo', this.vendorInfo);
				this.tabbar = this.vendorInfo.categoryInfoVoList
				this.tabbar.forEach(
					item=>{
						item["productVendorVoList"].forEach(item1=>{
							item1['sale'] = 0
						})
					}
				)
				// 把用户原来选择的物品复原
				this.vendorItemList.forEach((vendorItem)=>{
					if (vendorItem.vendorId == this.vendorId) {
						// 复原购物车
						this.itemList = vendorItem.itemList
						console.log(this.itemList);
						// 复原页面
						this.itemList.forEach((item1)=>{
							this.tabbar.forEach((product)=>{
								product.productVendorVoList.forEach((item2)=>{
									if (item1.productId === item2.productId) {
										item2.sale = item1.sale
									}
								})
							})
						})
						console.log(this.tabbar)
						return
					}
				})
				// 设置头部背景
				this.navbarBackground.background = `url(${this.vendorInfo.vendorImg}) repeat`
				this.$nextTick(() => {
					// 重新显示公告栏
					this.$refs.collapse.init()
				})
			},
			// 提交订单
			toSubmitOrderPage() {
				if (this.totalCount == 0) {
					uni.showToast({
						title: '请先选择商品',
						icon: 'error'
					})
					return
				}
				if (this.$u.test.isEmpty(this.vuex_token)) {
					this.$u.route({
						url: 'pages/login/login',
					})
					return
				}
				// console.log('vendor', this.itemList)
				this.$u.route({
					url: 'pages/submitOrder/submitOrder',
					params: {
						itemList: encodeURIComponent(JSON.stringify(this.itemList)),
						totalCount: this.totalCount,
						totalPrice: this.totalPrice,
						vendorName: this.vendorInfo.vendorName,
						vendorId: this.vendorId
					}
				})
			},
			// 增加一个商品
			plusOne(item1) {
				if(item1.sale >= 15) {
					uni.showToast({
						icon: 'error',
						title: '限购15'
					})
					return
				}
				// 修改tabbar商品列表中的sale值
				let isFind = false
				item1.sale += 1
				this.itemList.forEach(item=>{
					if (item.productId === item1.productId) {
						item.sale += 1
						isFind = true
						return
					}
				})
				if (!isFind) {
					this.itemList.push(this.$u.deepClone(item1))
				}
				console.log(this.itemList)
				// 修改vendorItemList
				isFind = false
				this.vendorItemList.forEach((vendorItem)=>{
					if (vendorItem.vendorId === this.vendorId) {
						vendorItem.itemList = this.itemList
						isFind = true
						return
					}
				})
				if (!isFind) {
					this.vendorItemList.push({
						"vendorId": this.vendorId,
						"itemList": this.itemList
					})
				}
				console.log(this.vendorItemList)
				this.$u.vuex('vendorItemList', this.vendorItemList)
			},
			// 减少一个商品
			minusOne(item1) {
				this.itemList.forEach(item=>{
					if (item.productId === item1.productId) {
						if (item.sale === 1) {
							item1.sale = 0
							// 删除元素
							this.itemList = this.itemList.filter(i=>i.productId !== item.productId)
						} else {
							item.sale -= 1
							item1.sale -= 1
						}
						return
					}
				})
				console.log(this.itemList)
				// 修改vendorItemList
				this.vendorItemList.forEach((vendorItem)=>{
					if (vendorItem.vendorId === this.vendorId) {
						vendorItem.itemList = this.itemList
						return
					}
				})
				console.log(this.vendorItemList)
				this.$u.vuex('vendorItemList', this.vendorItemList)
			},
			changeTab(index) {
				this.currentTab = index
				if (index === 1) {
					this.getCommentList()
				}
			},
			// 点击左边的栏目切换
			async swichMenu(index) {
				if(this.arr.length == 0) {
					await this.getMenuItemTop();
				}
				if (index == this.currentOption) return;
				this.scrollRightTop = this.oldScrollTop;
				this.$nextTick(function(){
					this.scrollRightTop = this.arr[index];
					this.currentOption = index;
					this.leftMenuStatus(index);
				})
			},
			// 获取一个目标元素的高度
			getElRect(elClass, dataVal) {
				new Promise((resolve, reject) => {
					const query = uni.createSelectorQuery().in(this);
					query.select('.' + elClass).fields({
						size: true
					}, res => {
						// 如果节点尚未生成，res值为null，循环调用执行
						if (!res) {
							setTimeout(() => {
								this.getElRect(elClass);
							}, 10);
							return;
						}
						this[dataVal] = res.height;
						resolve();
					}).exec();
				})
			},
			// 观测元素相交状态
			async observer() {
				this.tabbar.map((val, index) => {
					let observer = uni.createIntersectionObserver(this);
					// 检测右边scroll-view的id为itemxx的元素与right-box的相交状态
					// 如果跟.right-box底部相交，就动态设置左边栏目的活动状态
					observer.relativeTo('.right-box', {
						top: 0
					}).observe('#item' + index, res => {
						if (res.intersectionRatio > 0) {
							let id = res.id.substring(4);
							this.leftMenuStatus(id);
						}
					})
				})
			},
			// 设置左边菜单的滚动状态
			async leftMenuStatus(index) {
				this.currentOption = index;
				// 如果为0，意味着尚未初始化
				if (this.menuHeight == 0 || this.menuItemHeight == 0) {
					await this.getElRect('menu-scroll-view', 'menuHeight');
					await this.getElRect('u-tab-item', 'menuItemHeight');
				}
				// 将菜单活动item垂直居中
				this.scrollTop = index * this.menuItemHeight + this.menuItemHeight / 2 - this.menuHeight / 2;
			},
			// 获取右边菜单每个item到顶部的距离
			getMenuItemTop() {
				new Promise(resolve => {
					let selectorQuery = uni.createSelectorQuery();
					selectorQuery.selectAll('.class-item').boundingClientRect((rects) => {
						// 如果节点尚未生成，rects值为[](因为用selectAll，所以返回的是数组)，循环调用执行
						if(!rects.length) {
							setTimeout(() => {
								this.getMenuItemTop();
							}, 10);
							return ;
						}
						rects.forEach((rect) => {
							// 这里减去rects[0].top，是因为第一项顶部可能不是贴到导航栏(比如有个搜索框的情况)
							this.arr.push(rect.top - rects[0].top);
							resolve();
						})
					}).exec()
				})
			},
			// 右边菜单滚动
			async rightScroll(e) {
				this.oldScrollTop = e.detail.scrollTop;
				if(this.arr.length == 0) {
					await this.getMenuItemTop();
				}
				if(this.timer) return ;
				if(!this.menuHeight) {
					await this.getElRect('menu-scroll-view', 'menuHeight');
				}
				setTimeout(() => { // 节流
					this.timer = null;
					// scrollHeight为右边菜单垂直中点位置
					let scrollHeight = e.detail.scrollTop + this.menuHeight / 2;
					for (let i = 0; i < this.arr.length; i++) {
						let height1 = this.arr[i];
						let height2 = this.arr[i + 1];
						// 如果不存在height2，意味着数据循环已经到了最后一个，设置左边菜单为最后一项即可
						if (!height2 || scrollHeight >= height1 && scrollHeight < height2) {
							this.leftMenuStatus(i);
							return ;
						}
					}
				}, 10)
			}
		}
	}
</script>

<style lang="scss" scoped>
.navigation {
	position: fixed;
	bottom: 0rpx;
	display: flex;
	width: 92%;
	justify-content: space-between;
	margin: 100rpx 20rpx 30rpx 30rpx;
	border: solid 2rpx #f2f2f2;
	border-radius: 50rpx;
	background-color: #fdf6ec;
	padding: 16rpx 0;
	.left {
		display: flex;
		font-size: 20rpx;
		.item {
			margin: 0 30rpx;
			&.car {
				text-align: center;
				position: relative;
				.car-num {
					position: absolute;
					top: -10rpx;
					right: -10rpx;
				}
			}
		}
	}
	.right {
		display: flex;
		font-size: 28rpx;
		.btn {
			line-height: 66rpx;
			padding: 0 30rpx;
			margin-right: 15rpx;
			border-radius: 36rpx;
			color: #ffffff;
		}
		.cart {
			background-color: #ed3f14;
			margin-right: 30rpx;
		}
		.buy {
			background-color: #ff7900;
		}
	}
}
</style>

<style lang="scss" scoped>
	.u-wrap {
		height: calc(100vh);
		/* #ifdef H5 */
		height: calc(100vh - var(--window-top));
		/* #endif */
		display: flex;
		flex-direction: column;
		background-color: #fff;
		border-radius: 35rpx;
		margin: 18rpx;
	}
	
	.canteen-title {
		font-size: 50rpx;
		font-weight: bold;
	}
	
	.canteen-score {
		font-size: 25rpx;
		color: #ff9900;
	}

	.u-search-box {
		padding: 18rpx 30rpx;
	}

	.u-menu-wrap {
		flex: 1;
		display: flex;
		overflow: hidden;
		background-color: #f6f6f6;
	}

	.u-search-inner {
		background-color: rgb(234, 234, 234);
		border-radius: 100rpx;
		display: flex;
		align-items: center;
		padding: 10rpx 16rpx;
	}

	.u-search-text {
		font-size: 26rpx;
		color: $u-tips-color;
		margin-left: 10rpx;
	}

	.u-tab-view {
		width: 200rpx;
		height: 100%;
	}

	.u-tab-item {
		height: 110rpx;
		background: #fff;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 26rpx;
		color: #444;
		font-weight: 400;
		line-height: 1;
	}

	.u-tab-item-active {
		position: relative;
		color: #000;
		font-size: 30rpx;
		font-weight: 600;
		background: #fdf6ec;
	}

	.u-tab-item-active::before {
		content: "";
		position: absolute;
		border-left: 4px solid $u-type-warning;
		height: 32rpx;
		left: 0;
		top: 39rpx;
	}

	.u-tab-view {
		height: 100%;
	}

	.right-box {
		background-color: rgb(250, 250, 250);
	}

	.page-view {
		padding: 16rpx;
	}

	.class-item {
		margin-bottom: 30rpx;
		background-color: #fff;
		padding: 16rpx;
		border-radius: 8rpx;
	}

	.class-item:last-child {
		min-height: 100vh;
	}

	.item-title {
		font-size: 26rpx;
		color: $u-main-color;
		font-weight: bold;
	}

	.item-menu-name {
		font-weight: bold;
		font-size: 30rpx;
		color: $u-main-color;
		margin: 8rpx 5rpx;
	}
	
	.item-menu-sale {
		font-size: 25rpx;
		color: $u-content-color;
		margin: 5rpx;
	}
	
	.item-menu-price {
		font-weight: bold;
		font-size: 28rpx;
		color: $u-type-error;
		margin: 5rpx;
		display: flex;
		justify-content: space-between;
	}
	
	.item-menu-plus {
		width: 40rpx;
		height: 40rpx;
	}

	.item-container {
		display: flex;
		flex-wrap: wrap;
	}

	.thumb-box {
		width: 100%;
		display: flex;
		align-items: left;
		flex-direction: row;
		margin-top: 20rpx;
		flex-wrap: nowrap;
	}

	.item-menu-image {
		width: 150rpx;
		height: 150rpx;
		border-radius: 20rpx;
		margin-right: 10rpx;
	}
	
	.swiper-box {
		flex: 1;
	}
	.swiper-item {
		height: 100%;
	}
	
	.comment-card {
		background-color: #fff;
	}
</style>
