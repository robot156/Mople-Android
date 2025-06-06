package com.moim.core.data.datasource.comment

import com.moim.core.common.util.JsonUtil.jsonOf
import com.moim.core.data.util.catchFlow
import com.moim.core.datamodel.CommentResponse
import com.moim.core.model.Comment
import com.moim.core.model.asItem
import com.moim.core.network.service.CommentApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CommentRepositoryImpl @Inject constructor(
    private val commentApi: CommentApi,
) : CommentRepository {

    override fun getComments(postId: String): Flow<List<Comment>> = catchFlow {
        emit(commentApi.getComments(postId).map(CommentResponse::asItem))
    }

    override fun createComment(postId: String, content: String): Flow<List<Comment>> = catchFlow {
        emit(commentApi.createComment(postId, jsonOf(KEY_CONTENTS to content)).map(CommentResponse::asItem))
    }

    override fun updateComment(postId: String,commentId: String, content: String): Flow<List<Comment>> = catchFlow {
        emit(commentApi.updateComment(postId, commentId, jsonOf(KEY_CONTENTS to content)).map(CommentResponse::asItem))
    }

    override fun deleteComment(commentId: String): Flow<Unit> = catchFlow {
        emit(commentApi.deleteComment(commentId))
    }

    override fun reportComment(commentId: String): Flow<Unit> = catchFlow {
        emit(commentApi.reportComment(jsonOf(KEY_COMMENT_ID to commentId, KEY_REASON to "")))
    }

    companion object {
        private const val KEY_COMMENT_ID = "commentId"
        private const val KEY_CONTENTS = "contents"
        private const val KEY_REASON = "reason"
    }
}