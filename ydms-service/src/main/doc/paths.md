
<a name="paths"></a>
## Paths

<a name="addusingpost"></a>
### add
```
POST /menu
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**menuDTO**  <br>*required*|menuDTO|[MenuDTO](#menudto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* menu-controller


<a name="listusingget_1"></a>
### list
```
GET /menu
```


#### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**all**  <br>*optional*|all|boolean|`"true"`|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* menu-controller


<a name="listusingget"></a>
### list
```
GET /menu/demo
```


#### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**all**  <br>*optional*|all|boolean|`"true"`|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* menu-controller


<a name="editusingput"></a>
### edit
```
PUT /menu/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**menuDTO**  <br>*required*|menuDTO|[MenuDTO](#menudto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* menu-controller


<a name="deleteusingdelete"></a>
### delete
```
DELETE /menu/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `*/*`


#### Tags

* menu-controller


<a name="addusingpost_1"></a>
### 添加角色
```
POST /role
```


#### Description
Add role


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**roleDTO**  <br>*required*|roleDTO|[RoleDTO](#roledto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO«Role»](#5f26b21001135e38874e933b0d0f2e3e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* Role interface


<a name="listusingget_2"></a>
### list
```
GET /role
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO«RoleListVO»](#9bfcd851c831a876d453551b505807a6)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* Role interface


<a name="detailusingget"></a>
### Get role detail
```
GET /role/{id}
```


#### Description
Get role detail via role id, long long long long


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|Role id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO«RoleVO»](#bf67dd9dee646f702dd9bbeb63852a4c)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* Role interface


<a name="editusingput_1"></a>
### edit
```
PUT /role/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**roleDTO**  <br>*required*|roleDTO|[RoleDTO](#roledto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO«Role»](#5f26b21001135e38874e933b0d0f2e3e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* Role interface


<a name="deleteusingdelete_1"></a>
### delete
```
DELETE /role/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `*/*`


#### Tags

* Role interface


<a name="addusingpost_2"></a>
### add
```
POST /user
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userDTO**  <br>*required*|userDTO|[UserDTO](#userdto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="checkusernameusingpost"></a>
### checkUsername
```
POST /user/checkUsername
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**checkUserDTO**  <br>*required*|checkUserDTO|[CheckUserDTO](#checkuserdto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="infousingget"></a>
### info
```
GET /user/info
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="listusingget_3"></a>
### list
```
GET /user/list
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**limit**  <br>*required*|limit|integer (int32)|
|**Query**|**page**  <br>*required*|page|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="loginusingpost"></a>
### login
```
POST /user/login
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**loginDTO**  <br>*required*|loginDTO|[LoginDTO](#logindto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="logoutusingpost"></a>
### logout
```
POST /user/logout
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="resetpasswordusingpost"></a>
### resetPassword
```
POST /user/resetPassword
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**resetDTO**  <br>*required*|resetDTO|[ResetDTO](#resetdto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="sendverifycodeusingpost"></a>
### sendVerifyCode
```
POST /user/sendVerifyCode
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**checkUserDTO**  <br>*required*|checkUserDTO|[CheckUserDTO](#checkuserdto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="detailusingget_1"></a>
### detail
```
GET /user/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="editusingput_2"></a>
### edit
```
PUT /user/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userDTO**  <br>*required*|userDTO|[UserDTO](#userdto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="deleteusingdelete_2"></a>
### delete
```
DELETE /user/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|id|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[BaseVO](#basevo)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `*/*`


#### Tags

* user-controller



