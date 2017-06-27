# tweety

# How use api:
## Add new post
To add new post for user send HTTP POST request e.g.: 
localhost:8080/post/{username} with body parameter 'message' as a key and some text as value.
If user's wall does not exist it will be created
## Show user posts
To show user messages (posts) send HTTP GET request e.g.:
localhost:8080/wall/{username}
## Show following posts
To show user following messages send HTTP GET request e.g.:
localhost:8080/timeline/{username}
## Show all posts
To show all messages observed by user send HTTP GET request e.g.:
localhost:8080/board/{username}
## Add following user posts
To add following user posts send HTTP POST request e.g.:
localhost:8080/follow/{who}/{whom}
where who - is nickname of observer user
and whom - is nickname of observed user
## Remove following user posts
To remove following user posts send HTTP DELETE request e.g.:
localhost:8080/unfollow/{who}/{whom}
with the same parameters as are for 'follow' method.
