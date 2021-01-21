# **JsonPlaceHolder**

### **[JsonPlaceHolder](https://jsonplaceholder.typicode.com)** 에서 제공하는 Api를 이용해 목록조회, 아이템 수정 및 삭제가 가능한 앱 만들기<br></br>
PostListActivity, PostDetailActivity와 PostModifyActivity로 구분 되어있습니다.<br>
각각의 뷰모델을 이용해 데이터를 불러오거나 갱신합니다.<br>
Coroutine을 이용해 비동기처리를 구현합니다.<br>
fake api이기 때문에 로컬에서의 수정 내역이 반영되지 않아 목록을 caching 해 이용합니다.<br>
수정된 아이템의 경우 Detail로 재진입시 서버와 동기화 시킵니다.<br>

- get posts : @GET https://jsonplaceholder.typicode.com/posts
    * option : _start={page}, _limit={per_page} 
- get post : @GET https://jsonplaceholder.typicode.com/posts/{postId}
- get comments : @GET https://jsonplaceholder.typicode.com/posts/{postId}/comments
- delete post : @DELETE https://jsonplaceholder.typicode.com/posts/{postId}
- patch post : @PATCH https://jsonplaceholder.typicode.com/posts/{postId}
    * json : {"title" : "hello"}
<br>

## **Language**
- **[Kotlin](https://kotlinlang.org)**
<br>

## **Architecture**
- MVVM
<br>

## **Library used**
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)**
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**
- **[DataBinding](https://developer.android.com/topic/libraries/data-binding)**
- **[LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle)**
- **[Retrofit2](https://square.github.io/retrofit/)**
- **[Moshi](https://github.com/square/moshi)**

