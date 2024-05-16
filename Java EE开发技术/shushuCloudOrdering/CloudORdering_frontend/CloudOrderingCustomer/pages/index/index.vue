<template>
	<view class="u-m-l-18 u-m-r-18">
		<u-search placeholder="校园美食尽在掌握" @click="toSearch" @custom="toSearch" :disabled="true" bg-color="#fff" class="u-m-t-10"></u-search>
		<view class="u-m-t-15">
			<view class="u-line-1"> 
				<u-button v-for="(word, index) in searchKeywords" :key="index"
				:custom-style="customButtonStyle" shape="circle"
				size="mini" hover-class="none" @click="toSearchDetailByCurrentKeyWord(word)">{{word}}</u-button>
			</view>
			<u-swiper :list="swiper_img_list" class="u-m-t-15"></u-swiper>

			<u-notice-bar mode="horizontal" :list="noticeList" class="u-m-t-15"></u-notice-bar>

			<u-grid :col="5" class="u-m-t-15">
				<u-grid-item :custom-style='gridStyle' v-for="(item, index) in gridList" :key="index" @click='toSearchDetailByCurrentKeyWord(item.text)'>
					<image :src="item.url" class="icon"></image>
					<view class="grid-text">{{item.text}}</view>
				</u-grid-item>
			</u-grid>

			<u-toast ref="uToast" />
			<u-sticky>
				<u-subsection :list="tabList" :current="currentTab" :animation="true" active-color="#ff9900"
					@change="sectionChange" class="u-m-t-15"></u-subsection>		
			</u-sticky>
			<view class="wrap">
				<u-waterfall v-model="vendorList" ref="uWaterfall">
					<template v-slot:left="{leftList}">
						<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="toCanteen(item.vendorId)">
							<u-lazy-load threshold="-450" border-radius="10" :image="item.vendorImg" :index="index" duration="100" :threshold="300"></u-lazy-load>
							<view class="demo-price">
								<view><u-icon name="star-fill"></u-icon>{{item.score}}</view>
								<text>￥{{item.averagePrice}}/人</text>
							</view>
							<view class="demo-price">
								<view>已售<u-count-to :start-val="0" :end-val="item.totalSold" :font-size="30"></u-count-to>单</view>
							</view>
							<view class="u-m-t-10">
								<u-tag type="primary" shape="circle" :text="item.announcement" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
							</view>
							<view class="demo-shop">
								{{item.vendorName}}
								<u-tag type="info" shape="circle" text="已打烊" class="u-m-t-15 u-m-l-10" size="mini" mode="dark" v-show="item.inBusiness === 2"/>
								<u-tag type="warning" shape="circle" text="营业中" class="u-m-t-15 u-m-l-10" size="mini" mode="dark" v-show="item.inBusiness === 1"/>
							</view>
						</view>
					</template>
					<template v-slot:right="{rightList}">
						<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="toCanteen(item.vendorId)">
							<u-lazy-load threshold="-450" border-radius="10" :image="item.vendorImg" :index="index"></u-lazy-load>
							<view class="demo-price">
								<view><u-icon name="star-fill"></u-icon>{{item.score}}</view>
								<text>￥{{item.averagePrice}}/人</text>
							</view>
							<view class="demo-price">
								<view>已售<u-count-to :start-val="0" :end-val="item.totalSold" :font-size="30"></u-count-to>单</view>
							</view>
							<view class="u-m-t-10">
								<u-tag type="primary" shape="circle" :text="item.announcement" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
							</view>
							<view class="demo-shop">
								{{item.vendorName}}
								<u-tag type="info" shape="circle" text="已打烊" class="u-m-t-15 u-m-l-10" size="mini" mode="dark" v-show="item.inBusiness === 2"/>
								<u-tag type="warning" shape="circle" text="营业中" class="u-m-t-15 u-m-l-10" size="mini" mode="dark" v-show="item.inBusiness === 1"/>
							</view>
						</view>
					</template>
				</u-waterfall>
				<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="reloadVendorList"></u-loadmore>
			</view>
			
			<u-back-top :scroll-top="scrollTop"></u-back-top>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				swiper_img_list: [{
						image: 'https://img.zcool.cn/community/0193bc5de4878fa80120686b4f4c9c.jpg@1280w_1l_2o_100sh.jpg',
						title: '超大汉堡'
					},{
						image: 'https://tse1-mm.cn.bing.net/th/id/R-C.285f8ab5e37a2921937695aa40b8f2a5?rik=hKwpVsZD5PisNw&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20200510%2f25058003_093911362087_2.jpg&ehk=UkTa1X3uLfVk2gWvV%2b7Or4q4geNRLqHVN%2buPwWoEsWk%3d&risl=&pid=ImgRaw&r=0',
						title: '大叔条'
					},
					{
						image: 'https://img.zcool.cn/community/015e295d7083c8a801202f17be689f.jpg@1280w_1l_2o_100sh.jpg',
						title: '刀削面'
					},
					{
						image: 'https://img-u-3.51miz.com/preview/muban/00/00/47/89/M-478936-2F20B513.jpg',
						title: '牛肉饭'
					},
					{
						image: 'https://tse1-mm.cn.bing.net/th/id/R-C.54bf0eee925d8aac24e00d16aec0c956?rik=a4wH9nA2r4hI4A&riu=http%3a%2f%2fimg.sccnn.com%2fbimg%2f341%2f24302.jpg&ehk=koWnNIbt3vkQlrs2AsrIWYZeaWc4xknpNrEe9nM2e6E%3d&risl=&pid=ImgRaw&r=0',
						title: '高级牛肉饭'
					}
				],
				gridStyle: {
					borderRadius: '20rpx',
					margin: '3rpx'
				},
				gridList: [
					{
						text: '奶茶',
						url: '../../static/tiandian.png'
					},{
						text: '炸鸡',
						url: '../../static/zhaji.png'
					},{
						text: '快乐',
						url: '../../static/fruit.png'
					},{
						text: '饭',
						url: '../../static/riliao.png'
					},{
						text: '早点',
						url: '../../static/baozi.png'
					},
				],
				tabList: [{
						name: '综合推荐'
					},
					{
						name: '价格优先'
					},
					{
						name: '销量优先'
					},
					{
						name: '好评优先'
					}
				],
				currentTab: 0,
				noticeList: [
					'校园食堂美味一点通',
					'订餐小时代，美味轻享受',
					'手机在身边，美味在指尖',
					'太极生八卦，指下生佳肴'
				],
				customButtonStyle: {
					margin: '3px', // 注意驼峰命名，并且值必须用引号包括，因为这是对象，
					backgroundColor: '#f5f7f9',
					color: '#1a1a1c',
					height: '20px'
				},
				scrollTop: 0,
				loadStatus: 'loadmore',
				vendorList: [],
				currentVendorPage: 1,
				// 排序参数
				sortedBy: {
					byPrice: 2,
					byScore: 2,
					byTotalSold: 1,
				},
			}
		},
		onLoad(options) {
			this.getVendorList()
		},
		onPullDownRefresh() {
			this.reloadVendorList()
			setTimeout(function () {
				uni.stopPullDownRefresh()
			}, 600);
		},
		onReachBottom() {
			this.loadStatus = 'loading'
			// 数据加载更多
			setTimeout(() => {
				this.currentVendorPage += 1
				let preLength = this.vendorList.length
				this.getVendorList()
				this.loadStatus = 'loadmore'
				let curLength = this.vendorList.length
				if(curLength === preLength) {
					this.$u.toast('没有更多了~')
					this.loadStatus = 'nomore'
				}
			}, 1000)
		},
		methods: {
			reloadVendorList() {
				this.vendorList = []
				this.$refs.uWaterfall.clear()
				this.currentVendorPage = 1
				this.getVendorList()
			},
			async getVendorList() {
				const result = await this.$u.api.getVendorList({
					sortedBy: this.sortedBy,
					page: {
						current: this.currentVendorPage, //当前页码数
						size: 10 //每一页信息数
					}
				})
				this.vendorList = this.vendorList.concat(result.vendorList)
				console.log(this.vendorList);
			},
			sectionChange(index) {
				this.currentTab = index
			},
			toSearch() {
				this.$u.route({
					url: 'pages/search/search'
				})
			},
			toSearchDetailByCurrentKeyWord(word) {
				this.$u.route({
					url: 'pages/searchDetail/searchDetail',
					params: {
						keyword: word
					}
				})
			},
			onPageScroll(e) {
				this.scrollTop = e.scrollTop;
			},
			toCanteen(cid) {
				console.log('toVendor: ', cid)
				this.$u.route({
					url: 'pages/vendor/vendor',
					params: {
						cid: cid
					}
				})
			}
		},
		watch: {
			currentTab(newVar) {
				switch(newVar) {
					case 0:
						this.sortedBy.byPrice = 2
						this.sortedBy.byScore = 2
						this.sortedBy.byTotalSold = 1
						break;
					case 1:
						this.sortedBy.byPrice = 1
						this.sortedBy.byScore = 2
						this.sortedBy.byTotalSold = 2
						break;
					case 2:
						this.sortedBy.byPrice = 2
						this.sortedBy.byScore = 2
						this.sortedBy.byTotalSold = 1
						break;
					case 3:
						this.sortedBy.byPrice = 2
						this.sortedBy.byScore = 1
						this.sortedBy.byTotalSold = 2
						break;
				}
				// 监测用户选项变化
				this.$refs.uToast.show({
					title: '加载中...',
					type: 'info',
					duration: 600,
					callback: ()=>{
						this.reloadVendorList()
					}
				})
				
			}
		}
	}
</script>

<style lang="scss" scoped>
	.grid-text {
		font-size: 25rpx;
		margin-top: 4rpx;
		color: #ff7a64;
		font-weight: bold;
	}
	.icon {
		width: 56rpx;
		height: 56rpx;
	}
</style>

<style>
	/* page不能写带scope的style标签中，否则无效 */
	page {
		background-color: #f4f4f5;
	}
</style>

<style lang="scss" scoped>
	.demo-warter {
		border-radius: 8px;
		margin: 5px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}

	.demo-image {
		width: 100%;
		border-radius: 4px;
	}

	.demo-price {
		font-size: 28rpx;
		color: $u-type-error;
		margin-top: 5px;
		display: flex;
		justify-content: space-between;
		flex-wrap: wrap;
	}
	
	.demo-shop {
		font-size: 30rpx;
		color: $u-main-color;
		font-weight: bold;
		margin-top: 5px;
	}
</style>
