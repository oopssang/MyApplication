import com.google.gson.annotations.SerializedName

data class Documents (
	@SerializedName("authors")
	val authors : List<String>,
	@SerializedName("contents")
	val contents : String,
	@SerializedName("datetime")
	val datetime : String,
	@SerializedName("isbn")
	val isbn : String,
	@SerializedName("price")
	val price : Int,
	@SerializedName("publisher")
	val publisher : String,
	@SerializedName("sale_price")
	val sale_price : Int,
	@SerializedName("status")
	val status : String,
	@SerializedName("thumbnail")
	val thumbnail : String,
	@SerializedName("title")
	val title : String,
	@SerializedName("translators")
	val translators : List<String>,
	@SerializedName("url")
	val url : String,

	var isLike : Boolean = false
)