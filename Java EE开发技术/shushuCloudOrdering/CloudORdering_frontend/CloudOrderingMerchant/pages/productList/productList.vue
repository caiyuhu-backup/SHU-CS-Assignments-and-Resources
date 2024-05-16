<template>
	<view class="u-wrap">
		<view class="u-menu-wrap">
			<scroll-view scroll-y scroll-with-animation class="u-tab-view menu-scroll-view" :scroll-top="scrollTop"
			 :scroll-into-view="itemId">
				<view v-for="(item,index) in tabbar" :key="index" class="u-tab-item" :class="[currentOption == index ? 'u-tab-item-active' : '']"
				 @tap.stop="swichMenu(index)">
					<text class="u-line-1">{{item.categoryName}}</text>
				</view>
			</scroll-view>
			<scroll-view :scroll-top="scrollRightTop" scroll-y scroll-with-animation class="right-box" @scroll="rightScroll">
				<view class="page-view">
					<view class="class-item" :id="'item' + index" v-for="(item, index) in tabbar" :key="index">
						<view class="item-title">
							<text>{{item.categoryName}}</text>
						</view>
						<view class="item-container">
							<view class="thumb-box" v-for="(item1, index1) in item.productVendorVoList" :key="index1" @click="changeProduct(item1)">
								<image class="item-menu-image" :src="item1.productImg"></image>
								<view>
									<view class="item-menu-name">
										{{item1.productName}}
									</view>
									<view class="item-menu-sale u-flex">
										<view class="u-m-r-28">
											销量:
											<u-count-to :start-val="0" :end-val="item1.soldNum" :decimals="0" separator=","
												:duration="600" font-size="30"></u-count-to>
										</view>
										<view>库存剩余: 
											<u-count-to :start-val="0" :end-val="item1.stock" :decimals="0" separator=","
												:duration="600" font-size="30"></u-count-to>
										</view>
									</view>
									<view class="item-menu-price">
										￥{{item1.sellPrice.toFixed(2)}}
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
		<u-popup v-model="showPopup" mode="top" :mask-close-able='false' :closeable='true' close-icon-pos='top-left'>
			<view class='center-card'>
				<u-form :model="form" ref="uForm" label-width='150' :label-style='labelStyle' class='u-m-l-18'>
					<u-form-item label="名称"><u-input v-model="form.productName" /></u-form-item>
					<u-form-item label="价格">
						<u-number-box class='u-m-r-12' v-model="form.sellPrice" :positive-integer='false' input-width='200'></u-number-box>
						元
					</u-form-item>
					<u-form-item label="库存">
						<u-number-box class='u-m-r-12' v-model="form.stock" :positive-integer='false' input-width='200'></u-number-box>
						个/份
					</u-form-item>
					<u-form-item label="状态">
						<view v-show="switchVal" style="color: #606266;">上架</view>
						<view v-show="!switchVal" style="color: #fa3534;">下架</view>
						<u-switch slot="right" v-model="switchVal" active-color="#f29100" inactive-color="#dee1e6" ></u-switch>
					</u-form-item>
					<u-form-item label="图片">
						<view class="wrap">
							<u-upload :custom-btn="true" ref="uUpload" :action="action" max-count='1' @on-uploaded='onUploaded' @on-choose-complete='onChoose'> 
								<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
									<u-icon name="photo" size="80" color="#c0c4cc"></u-icon>
								</view>
							</u-upload>
						</view>
					</u-form-item>
					<u-button class='u-m-t-50' type='warning' @click='submit' style='width: 300rpx;'>点击提交</u-button>
				</u-form>
			</view>
		</u-popup>
		<u-modal v-model="showModal" :show-title='false' :show-confirm-button='false' width="26%" :mask-close-able='true'>
			<u-circle-progress active-color="#18b566" :percent="100" style='text-align: center;'>
				<view>操作中</view>
			</u-circle-progress>
		</u-modal>
		
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
				// 商品列表
				tabbar: [],
				showPopup: false,
				showModal: false,
				form: {},
				switchVal: false,
				action: 'http://upload.itbear666.top:8081/upload/singleFile',
				isuploaded: false,
				isChoose: false,
				imgUrl: '',
				labelStyle: {
					'color': '#ff9901',
					'fontWeight': 'bold',
					'fontSize': '32rpx'
				},
				
			}
		},
		async onShow() {
			await this.getVendorInfo()
		},
		async onReady() {
			await this.getMenuItemTop()
		},
		onLoad() {
			
		},
		methods: {
			async submit() {
				this.showModal = true
				if (this.isChoose && this.imgUrl === '') {
					this.$u.toast('请等待图片上传完毕！')
					return
				}
				console.log({
					productId: this.form.productId,
					productName: this.form.productName,
					productImg: this.imgUrl,
					sellPrice: this.form.sellPrice,
					stock: this.form.stock,
					productStatus: this.switchVal === true ? 1 : 0
				});
				const res = await this.$u.api.updateProduct({
					productId: this.form.productId,
					productName: this.form.productName,
					productImg: this.imgUrl,
					sellPrice: this.form.sellPrice,
					stock: this.form.stock,
					productStatus: this.switchVal === true ? 1 : 0
				})	
				setTimeout(()=>{
					this.form = {}
					this.showPopup = false
					this.$u.toast('修改成功~')
					this.showModal = false
					this.imgUrl = ''
					this.getVendorInfo()
				}, 1800)
			},
			onUploaded(lists, name) {
				console.log('上传成功', lists, name)
				try {
					if (lists.length > 0) {
						this.imgUrl = lists[0].response.data.url
					}
					this.isuploaded = true
					this.$u.toast('图片上传成功~')
				} catch(e) {
					this.$u.toast('图片上传失败！')
				}
			},
			onChoose() {
				this.isChoose = true
			},
			changeProduct(product) {
				this.showPopup = true
				console.log(product)
				this.form = this.$u.deepClone(product)
				this.switchVal = product.productStatus === 1 ? true : false
			},
			async getVendorInfo() {
				const result = await this.$u.api.getVendorBriefInfo(this.vuex_merchant_user.vendorId)
				this.vendorInfo = result
				console.log(this.vendorInfo)
				this.tabbar = this.vendorInfo.categoryInfoVoList
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
	.u-wrap {
		height: calc(100vh);
		/* #ifdef H5 */
		height: calc(100vh - var(--window-top));
		/* #endif */
		display: flex;
		flex-direction: column;
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
		font-size: 36rpx;
		color: #ff9901;
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
		font-size: 30rpx;
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
	
	.center-card {
		height: 900rpx;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin: 18rpx;
		padding: 10rpx;
	}
	.wrap {
		padding: 24rpx;
	}
	
	.slot-btn {
		width: 341rpx;
		height: 240rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: rgb(244, 245, 246);
		border-radius: 10rpx;
	}
	
	.slot-btn__hover {
		background-color: rgb(235, 236, 238);
	}
</style>
