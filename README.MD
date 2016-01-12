# APIs

## /register  注册一个用户

**request:**

	/api/register/{whoami}

**response:**
	
`success`	

		{
		    "name"   : "{whoami}"
	    	"result" : "ok",
	    	"status" : "0,0,0,0,0,0,2,0,4,0,0,0,0,0,0,0"
		}

`failure`
		
		{
	    	"result" : "failed"
	    	"message" : " xxxx alread used."
		}

## /reset 重置

**request:**

	/api/reset/{whoami}
	
**response:**

		{
		    "name"   : "{whoami}"
	    	"result" : "ok",
	    	"status" : "0,0,0,0,0,0,2,0,4,0,0,0,0,0,0,0"
		}
	
	
## /move  移动方式

**request:**

	/api/move/{direction}

**response:**

`success`	

		{
		    "name"   : "{whoami}"
	    	"result" : true,
	    	"status" : "2, 4, 8, 16, 0, 0, 0, 0, 2, 4, 8, 16, 0, 0, 0, 0",
	    	"message" : "move succeed.",
	    	"score" : 16
		}

`failure`
		
		{
		    "name"   : "{whoami}"
	    	"result" : false,
	    	"status" : "2, 4, 8, 16, 0, 0, 0, 0, 2, 4, 8, 16, 0, 0, 0, 0",
	    	"message" : " xxxx xxx",
	    	"score" : -1
		}

## scores 获取某个用户分数


**request:**

	/api/score/{user}
	
**response:**
	
	{
		"name" : "user",
		"score" : "5000"
	}


## scores 获取一页用户分数	

**request:**
	
	/api/scores?offset=1&size=10

**response:**	
	
	[
		{"beibei" : "1234"},
		{"jignjing" : "1212"},
		{"huanhuan" : "3212"},
		{"yingying" : "4320"},
		{"nini" : "50000"}					
	]
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
