package com.koresuniku.wishmaster_v4.core.data.threads

import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListJsonSchemaResponse

/**
 * Created by koresuniku on 01.01.18.
 */

object ThreadsMapper {

    fun mapResponseToThreadListData(schema: ThreadListJsonSchemaResponse): ThreadListData {
        val threadListData = ThreadListData()

        threadListData.setBoardName(schema.boardName)
        threadListData.setDefaultName(schema.defaultName)
        threadListData.setBoardList(schema.threads)

        return threadListData
    }
}