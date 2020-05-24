
<a name="definitions"></a>
## Definitions

<a name="basevo"></a>
### BaseVO
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|状态码  <br>**Example** : `200`|integer (int32)|
|**data**  <br>*optional*||object|
|**message**  <br>*required*|消息  <br>**Example** : `"success"`|string|


<a name="9bfcd851c831a876d453551b505807a6"></a>
### BaseVO«RoleListVO»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|状态码  <br>**Example** : `200`|integer (int32)|
|**data**  <br>*optional*||[RoleListVO](#rolelistvo)|
|**message**  <br>*required*|消息  <br>**Example** : `"success"`|string|


<a name="bf67dd9dee646f702dd9bbeb63852a4c"></a>
### BaseVO«RoleVO»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|状态码  <br>**Example** : `200`|integer (int32)|
|**data**  <br>*optional*||[RoleVO](#rolevo)|
|**message**  <br>*required*|消息  <br>**Example** : `"success"`|string|


<a name="5f26b21001135e38874e933b0d0f2e3e"></a>
### BaseVO«Role»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|状态码  <br>**Example** : `200`|integer (int32)|
|**data**  <br>*optional*||[Role](#role)|
|**message**  <br>*required*|消息  <br>**Example** : `"success"`|string|


<a name="checkuserdto"></a>
### CheckUserDTO

|Name|Schema|
|---|---|
|**username**  <br>*optional*|string|


<a name="logindto"></a>
### LoginDTO
登录传参


|Name|Description|Schema|
|---|---|---|
|**password**  <br>*required*|密码  <br>**Example** : `"xxxxxx"`|string|
|**username**  <br>*required*|用户名  <br>**Example** : `"admin"`|string|


<a name="menudto"></a>
### MenuDTO

|Name|Schema|
|---|---|
|**icon**  <br>*optional*|string|
|**id**  <br>*optional*|integer (int32)|
|**name**  <br>*optional*|string|
|**orderNum**  <br>*optional*|integer (int32)|
|**permission**  <br>*optional*|string|
|**pid**  <br>*optional*|integer (int32)|
|**type**  <br>*optional*|integer (int32)|
|**url**  <br>*optional*|string|


<a name="pagevo"></a>
### PageVO

|Name|Schema|
|---|---|
|**page**  <br>*optional*|integer (int32)|
|**size**  <br>*optional*|integer (int32)|
|**totalCount**  <br>*optional*|integer (int64)|
|**totalPage**  <br>*optional*|integer (int32)|


<a name="resetdto"></a>
### ResetDTO

|Name|Schema|
|---|---|
|**code**  <br>*optional*|string|
|**email**  <br>*optional*|string|
|**password**  <br>*optional*|string|


<a name="role"></a>
### Role

|Name|Schema|
|---|---|
|**createTime**  <br>*optional*|string (date-time)|
|**id**  <br>*optional*|integer (int32)|
|**remark**  <br>*optional*|string|
|**roleName**  <br>*optional*|string|
|**updateTime**  <br>*optional*|string (date-time)|


<a name="roledto"></a>
### RoleDTO

|Name|Schema|
|---|---|
|**id**  <br>*optional*|integer (int32)|
|**menuIds**  <br>*optional*|< integer (int32) > array|
|**remark**  <br>*optional*|string|
|**roleName**  <br>*optional*|string|


<a name="rolelistvo"></a>
### RoleListVO

|Name|Schema|
|---|---|
|**list**  <br>*optional*|< [Role](#role) > array|
|**page**  <br>*optional*|[PageVO](#pagevo)|


<a name="rolevo"></a>
### RoleVO

|Name|Schema|
|---|---|
|**createTime**  <br>*optional*|string (date-time)|
|**id**  <br>*optional*|integer (int32)|
|**menuIds**  <br>*optional*|< integer (int32) > array|
|**remark**  <br>*optional*|string|
|**roleName**  <br>*optional*|string|
|**updateTime**  <br>*optional*|string (date-time)|


<a name="userdto"></a>
### UserDTO

|Name|Schema|
|---|---|
|**email**  <br>*optional*|string|
|**id**  <br>*optional*|integer (int32)|
|**roleIds**  <br>*optional*|< integer (int32) > array|
|**username**  <br>*optional*|string|



