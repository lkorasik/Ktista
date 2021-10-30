# Database

Entity:
- User
- Post
- Comment
- Thread

User:
- id: long primary key (?)
- first name: string not null
- last name: string not null
- email: string not null
- nickname: string not null
- password_hash: long (?)
- follow: list long
- followers: list long

Post:
- id: long primary key (?)
- author_id: long
- photo_id: string
- thread_id: long
- text: string
- date: date
- rating_like: list long
- rating_dislike: lost long

Comment:
- id: long primary key (?)
- thread_id: long
- author_id: long
- text: string 
- date: string

Thread:
- id: long primary key (?)
- post_id: long
- comments_id: list long