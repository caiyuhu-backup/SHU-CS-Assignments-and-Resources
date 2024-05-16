<template>
	<view class="wrap u-m-l-18 u-m-r-18">
		<u-navbar>
			<u-search placeholder="校园美食尽在掌握" v-model="keyword" 
				@custom="searchDetail" @search="searchDetail" 
				height=72 focus style="width: 650rpx;">
			</u-search>
		</u-navbar>
		<u-dropdown active-color="#ff9900" :border-bottom="true" :border-radius="20">
			<u-dropdown-item v-model="sortedBy.byPrice" title="综合排序" :options="options1" @change="reloadVendorList"></u-dropdown-item>
			<u-dropdown-item v-model="sortedBy.byTotalSold" title="销量优先" :options="options2" @change="reloadVendorList"></u-dropdown-item>
			<u-dropdown-item v-model="sortedBy.byScore" title="评分优先" :options="options3" @change="reloadVendorList"></u-dropdown-item>
		</u-dropdown>
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
						<u-tag type="error" shape="circle" text="好吃" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
						<u-tag type="primary" shape="circle" text="自营" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
						<u-tag type="warning" shape="circle" text="便宜" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
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
						<u-tag type="error" shape="circle" text="好吃" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
						<u-tag type="primary" shape="circle" text="自营" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
						<u-tag type="warning" shape="circle" text="便宜" class="u-m-r-10 u-m-b-10" size="mini" mode="dark"/>
					</view>
					<view class="demo-shop">
						{{item.vendorName}}
						<u-tag type="info" shape="circle" text="已打烊" class="u-m-t-15 u-m-l-10" size="mini" mode="dark" v-show="item.inBusiness === 2"/>
						<u-tag type="warning" shape="circle" text="营业中" class="u-m-t-15 u-m-l-10" size="mini" mode="dark" v-show="item.inBusiness === 1"/>
					</view>
				</view>
			</template>
		</u-waterfall>
		<u-empty mode="search" v-show="!vendorList"></u-empty>
		<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="reloadVendorList" v-show="vendorList"></u-loadmore>
		<u-back-top :scroll-top="scrollTop" v-show="vendorList"></u-back-top>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				loadStatus: 'loadmore',
				vendorList: [],
				scrollTop: 0,
				currentVendorPage: 1,
				// 排序参数
				sortedBy: {
					byPrice: 2,
					byScore: 2,
					byTotalSold: 1,
				},
				value1: 1,
				value2: 2,
				options1: [
					{
						label: '价格降序',
						value: 1,
					},
					{
						label: '价格升序',
						value: 2,
					}
				],
				options2: [{
						label: '销量降序',
						value: 1,
					},
					{
						label: '销量升序',
						value: 2,
					},
				],
				options3: [{
						label: '评分降序',
						value: 1,
					},
					{
						label: '评分升序',
						value: 2,
					},
				],
			}
		},
		onLoad(options) {
			this.keyword = options.keyword
			this.getVendorList()
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
				console.log(this.sortedBy);
				const result = await this.$u.api.getVendorList({
					text: this.keyword,
					sortedBy: this.sortedBy,
					page: {
						current: this.currentVendorPage, //当前页码数
						size: 6 //每一页信息数
					}
				})
				this.vendorList = this.vendorList.concat(result.vendorList)
				console.log(this.vendorList)
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
			},
			searchDetail() {
				if (this.$u.test.isEmpty(this.keyword)) {
					this.$u.toast('输入框不能为空~')
				} else {
					if (this.searchKeywords.indexOf(this.keyword) === -1) {
						this.searchKeywords.push(this.keyword)
						this.$u.vuex('searchKeywords', this.searchKeywords)
					}
					this.reloadVendorList()
				}
			},
		}
	}
</script>

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
