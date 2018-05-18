package com.twtstudio.service.dishesreviews.model

/**
 * Created by SGXM on 2018/5/16.
 */
/////////////////////////////////////Home//////////////////////////////////////
data class DishesHomeBean(
        val top10Food: List<Top10Food> = listOf(),
        val canteenList: List<Canteen> = listOf(),
        val goodComment: List<GoodComment> = listOf()
)


data class Top10Food(
        val food_id: Int = 0, //19
        val food_statement: String = "", //中国名菜
        val food_name: String = "", //辣炒牛肉
        val food_time: String = "", //晚餐
        val food_price: Int = 0, //10
        val food_picture_address: String = "", //null
        val canteen_id: Int = 0, //19
        val canteen_name: String = "", //棠园
        val food_floor: String = "", //1
        val food_window: Int = 0, //1
        val food_comment_number: Int = 0, //1
        val food_praise_number: Int = 0, //0
        val food_total_score: Int = 0, //5
        val food_score: Int = 0, //5
        val food_collect_number: Any = Any(), //null
        val created_at: String = "", //2018-05-05 12:40:11
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-05-05 14:01:22
)

data class GoodComment(
        val comment_id: Int = 0, //36
        val food_name: String = "", //null
        val food_id: Int = 0, //15
        val commenter_id: Int = 0, //1
        val commenter_name: String = "", //Dean
        val food_time: String = "", //午餐
        val food_score: Int = 0, //5
        val comment_content: String = "", //这个东西太和我的胃口了
        val comment_praise_number: Int = 0, //1
        val comment_is_anonymous: Int = 0, //0
        val picture_address1: String = "",
        val picture_address2: String = "",
        val picture_address3: String = "",
        val picture_address4: String = "",
        val created_at: String = "", //2018-05-05 13:05:05
        val deleted_at: Any = Any(), //null
        val updated_at: String = "", //2018-05-05 14:35:39
        val isCurrentUserPraised: Boolean = false //true
)

data class Canteen(
        val canteen_id: Int = 0, //18
        val canteen_name: String = "", //竹园
        val canteen_phone: Int = 0, //1425367987
        val canteen_address: String = "", //天大北洋园
        val canteen_time: String = "", //bantian
        val canteen_floor: Int = 0, //2
        val created_at: String = "", //2018-05-05 12:19:54
        val updated_at: String = "", //2018-05-05 12:19:54
        val deleted_at: Any = Any(), //null
        val canteen_picture_address: String = "" //null
)
/////////////////////////////////////Home/////////////////////////////////////

/////////////////////////////////////搜索页面////////////////////////////////////

data class DishesSearchBean(
        val searchResult: List<SearchResult> = listOf()
)

data class SearchResult(
        val food_id: Int = 0, //4
        val food_statement: Any = Any(), //null
        val food_name: String = "", //HD
        val food_time: Any = Any(), //null
        val food_price: Int = 0, //10
        val food_picture_address: String = "", //desktop
        val canteen_id: Int = 0, //2
        val canteen_name: Any = Any(), //null
        val food_floor: String = "", //1
        val food_window: Int = 0, //10
        val food_comment_number: Int = 0, //2
        val food_praise_number: Int = 0, //18
        val food_total_score: Any = Any(), //null
        val food_score: Int = 0, //6
        val food_collect_number: Int = 0, //1
        val created_at: String = "", //2018-04-10 14:35:52
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-04-26 00:46:53
)
/////////////////////////////////////搜索页面////////////////////////////////////

/////////////////////////////////////食堂页/////////////////////////////////////

data class DishesCanteenBean(
        val canteenInfo: CanteenInfo = CanteenInfo(),
        val canteenScore: Int = 0, //4
        val foodRecommend: List<FoodRecommend> = listOf(),
        val foodList: List<Food> = listOf(),
        val latestFood: List<LatestFood> = listOf()
)

data class CanteenInfo(
        val canteen_id: Int = 0, //2
        val canteen_name: String = "", //兰
        val canteen_phone: Int = 0, //12345456
        val canteen_address: String = "", //兰园
        val canteen_time: String = "", //123
        val canteen_floor: Int = 0, //2
        val created_at: String = "", //2018-04-12 10:39:33
        val updated_at: String = "", //2018-04-12 10:39:33
        val deleted_at: Any = Any(), //null
        val canteen_picture_address: Any = Any() //null
)

data class LatestFood(
        val food_id: Int = 0, //4
        val food_statement: Any = Any(), //null
        val food_name: String = "", //HD
        val food_time: Any = Any(), //null
        val food_price: Int = 0, //10
        val food_picture_address: String = "", //desktop
        val canteen_id: Int = 0, //2
        val canteen_name: Any = Any(), //null
        val food_floor: String = "", //1
        val food_window: Int = 0, //10
        val food_comment_number: Int = 0, //2
        val food_praise_number: Int = 0, //18
        val food_total_score: Any = Any(), //null
        val food_score: Int = 0, //6
        val food_collect_number: Int = 0, //1
        val created_at: String = "", //2018-04-10 14:35:52
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-04-26 00:46:53
)

data class Food(
        val food_id: Int = 0, //4
        val food_statement: Any = Any(), //null
        val food_name: String = "", //HD
        val food_time: Any = Any(), //null
        val food_price: Int = 0, //10
        val food_picture_address: String = "", //desktop
        val canteen_id: Int = 0, //2
        val canteen_name: Any = Any(), //null
        val food_floor: String = "", //1
        val food_window: Int = 0, //10
        val food_comment_number: Int = 0, //2
        val food_praise_number: Int = 0, //18
        val food_total_score: Any = Any(), //null
        val food_score: Int = 0, //6
        val food_collect_number: Int = 0, //1
        val created_at: String = "", //2018-04-10 14:35:52
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-04-26 00:46:53
)

data class FoodRecommend(
        val food_id: Int = 0, //5
        val food_statement: Any = Any(), //null
        val food_name: String = "", //F
        val food_time: Any = Any(), //null
        val food_price: Int = 0, //18
        val food_picture_address: String = "", //desktop
        val canteen_id: Int = 0, //2
        val canteen_name: Any = Any(), //null
        val food_floor: Any = Any(), //null
        val food_window: Int = 0, //10
        val food_comment_number: Int = 0, //15
        val food_praise_number: Int = 0, //50
        val food_total_score: Int = 0, //24
        val food_score: Int = 0, //2
        val food_collect_number: Int = 0, //1
        val created_at: String = "", //2018-04-11 05:28:19
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-05-03 00:14:34
)
/////////////////////////////////////食堂页/////////////////////////////////////

/////////////////////////////////////菜品页/////////////////////////////////////

data class DishesFoodBean(
        val foodInfo: FoodInfo = FoodInfo(),
        val foodMark: FoodMark = FoodMark(),
        val isPraisedFood: Boolean = false, //true
        val isCollectedFood: Boolean = false, //true
        val comment: List<Comment> = listOf()
)

data class FoodInfo(
        val food_id: Int = 0, //15
        val food_statement: String = "", //中国名菜
        val food_name: String = "", //鱼香鸡丝
        val food_time: String = "", //晚餐
        val food_price: Int = 0, //10
        val food_picture_address: String = "", //null
        val canteen_id: Int = 0, //15
        val canteen_name: String = "", //梅园
        val food_floor: String = "", //1
        val food_window: Int = 0, //1
        val food_comment_number: Int = 0, //3
        val food_praise_number: Int = 0, //1
        val food_total_score: Int = 0, //15
        val food_score: Int = 0, //5
        val food_collect_number: Int = 0, //1
        val created_at: String = "", //2018-05-05 12:35:21
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-05-05 14:34:02
)

data class FoodMark(
        val spicy: Int = 0, //3
        val fine: Int = 0, //3
        val attitude: Int = 0 //3
)

data class Comment(
        val comment_id: Int = 0, //36
        val food_id: Int = 0, //15
        val commenter_id: Int = 0, //1
        val commenter_name: String = "", //Dean
        val food_time: String = "", //午餐
        val food_score: Int = 0, //5
        val comment_content: String = "", //这个东西太和我的胃口了
        val comment_praise_number: Int = 0, //1
        val comment_is_anonymous: Int = 0, //0
        val picture_address1: String = "",
        val picture_address2: String = "",
        val picture_address3: String = "",
        val picture_address4: String = "",
        val created_at: String = "", //2018-05-05 13:05:05
        val deleted_at: Any = Any(), //null
        val updated_at: String = "", //2018-05-05 14:35:39
        val isCurrentUserPraised: Boolean = false //true
)

/////////////////////////////////////菜品页/////////////////////////////////////

/////////////////////////////////////评价页/////////////////////////////////////

data class DishesEvaluateBean(
        val comment: EvaComment = EvaComment(),
        val commentMark: List<CommentMark> = listOf()
)

data class CommentMark(
        val food_id: Int = 0, //11
        val comment_id: Int = 0, //34
        val mark_id: Int = 0 //1
)

data class EvaComment(
        val food_id: String = "", //11
        val commenter_id: String = "", //2
        val commenter_name: Any = Any(), //null
        val food_time: String = "", //早餐
        val food_score: String = "", //4
        val comment_content: String = "", //这个东西太和我的胃口了
        val comment_is_anonymous: String = "", //0
        val comment_praise_number: Int = 0, //0
        val updated_at: String = "", //2018-05-03 20:27:21
        val created_at: String = "", //2018-05-03 20:27:21
        val comment_id: Int = 0 //34
)
/////////////////////////////////////评价页/////////////////////////////////////

/////////////////////////////////////添加菜品/////////////////////////////////////

data class DishesAddFoodBean(
        val food_name: String = "", //鱼香鸡丝
        val food_statement: String = "", //中国名菜
        val food_time: String = "", //晚餐
        val food_price: String = "", //10
        val food_picture_address: String = "", //null
        val canteen_id: String = "", //6
        val canteen_name: String = "", //梅园
        val food_floor: String = "", //1
        val food_window: String = "", //1
        val food_comment_number: Int = 0, //0
        val food_praise_number: Int = 0, //0
        val food_total_score: Int = 0, //0
        val food_score: Int = 0, //0
        val updated_at: String = "", //2018-05-03 21:03:27
        val created_at: String = "", //2018-05-03 21:03:27
        val food_id: Int = 0 //12
)
/////////////////////////////////////添加菜品/////////////////////////////////////

/////////////////////////////////////点赞/////////////////////////////////////
data class DishesLikeBean(
        val food_id: Int = 0, //11
        val food_statement: String = "", //中国名菜
        val food_name: String = "", //鱼香鸡丝
        val food_time: String = "", //晚餐
        val food_price: Int = 0, //10
        val food_picture_address: String = "", //null
        val canteen_id: Int = 0, //6
        val canteen_name: String = "", //梅园
        val food_floor: String = "", //1
        val food_window: Int = 0, //1
        val food_comment_number: Int = 0, //3
        val food_praise_number: Int = 0, //1
        val food_total_score: Int = 0, //12
        val food_score: Int = 0, //4
        val food_collect_number: Any = Any(), //null
        val created_at: String = "", //2018-05-03 20:20:49
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-05-03 21:05:21
)
/////////////////////////////////////点赞/////////////////////////////////////

/////////////////////////////////////收藏菜品/////////////////////////////////////

//api有问题
//data class DishesCollectBean(
//		val error_code: Int = 0, //-1
//		val message: String = "",
//		val data: List<Data> = listOf()
//)
//
//data class Data(
//		val user_id: String = "", //2
//		val food_id: String = "", //11
//		val updated_at: String = "", //2018-05-03 21:06:01
//		val created_at: String = "", //2018-05-03 21:06:01
//		val collect_id: Int = 0 //10
//)
/////////////////////////////////////收藏菜品/////////////////////////////////////

/////////////////////////////////////个人主页 我的评论/////////////////////////////////////


data class DishesAccountBean(
        val commentNumber: Int = 0, //4
        val comment: List<MyComment> = listOf()
)

data class MyComment(
        val comment_id: Int = 0, //15
        val food_id: Int = 0, //5
        val commenter_id: Int = 0, //1
        val commenter_name: Any = Any(), //null
        val food_time: String = "", //早餐
        val food_score: Int = 0, //4
        val comment_content: String = "", //这个东西太和我的胃口了
        val comment_praise_number: Int = 0, //0
        val comment_is_anonymous: Int = 0, //0
        val picture_address1: Any = Any(), //null
        val picture_address2: Any = Any(), //null
        val picture_address3: Any = Any(), //null
        val picture_address4: Any = Any(), //null
        val created_at: String = "", //2018-05-03 00:13:26
        val deleted_at: Any = Any(), //null
        val updated_at: String = "" //2018-05-03 00:13:26
)
/////////////////////////////////////个人主页 我的评论/////////////////////////////////////
/////////收藏菜品/////////////////////////////////////