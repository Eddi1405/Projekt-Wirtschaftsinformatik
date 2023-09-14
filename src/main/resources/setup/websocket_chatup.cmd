rem author: Michael Hartmann

rem Add Adam
curl -v -X POST localhost:8080/register -H "Content-type:application/json" -d "{\"username\":\"Adam\",\"password\":\"\",\"role\":\"student\"}"
rem Add Steve
curl -v -X POST localhost:8080/register -H "Content-type:application/json" -d "{\"username\":\"Steve\",\"password\":\"\",\"role\":\"student\"}"
rem Add personal chat
curl -v -X POST localhost:8080/chats/create -H "Content-type:application/json" -d "{\"chatName\":\"Personal Chat\",\"chatType\":\"personal\"}"
rem Add room chat
curl -v -X POST localhost:8080/chats/create -H "Content-type:application/json" -d "{\"chatName\":\"Room Chat\",\"chatType\":\"room\"}"
rem Register the two users
curl -v -X PATCH localhost:8080/chats/register/1 -H "Content-type:application/json" -d 1
curl -v -X PATCH localhost:8080/chats/register/1 -H "Content-type:application/json" -d 2
rem Add new Chat none of the users are registered with
curl -v -X POST localhost:8080/chats/create -H "Content-type:application/json" -d "{\"chatName\":\"OtherChat\",\"chatType\":\"personal\"}"
pause