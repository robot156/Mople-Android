package com.moim.core.data.datasource.comment

import com.moim.core.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getComments(postId: String): Flow<List<Comment>>

    fun createComment(
        postId: String,
        content: String
    ): Flow<List<Comment>>

    fun updateComment(
        postId: String,
        commentId: String,
        content: String,
    ): Flow<List<Comment>>

    fun deleteComment(commentId: String): Flow<Unit>

    fun reportComment(commentId: String): Flow<Unit>
}
