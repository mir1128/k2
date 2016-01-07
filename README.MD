# APIs

## /register  注册一个用户

**request:**

	/register/{whoami}

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
	
## /turn  移动方式

**request:**

	/move/{direction}

**response:**

`success`	

		{
		    "name"   : "{whoami}"
	    	"result" : "ok",
	    	"status" : "2, 4, 8, 16, 0, 0, 0, 0, 2, 4, 8, 16, 0, 0, 0, 0"
		}

`failure`
		
		{
		    "name"   : "{whoami}"
	    	"result" : "failed",
	    	"status" : "2, 4, 8, 16, 0, 0, 0, 0, 2, 4, 8, 16, 0, 0, 0, 0",
	    	"message" : " xxxx xxx"
		}



	
