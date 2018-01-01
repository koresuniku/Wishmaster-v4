package com.koresuniku.wishmaster_v4.core.data.threads

import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListJsonSchemaCatalogResponse
import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListJsonSchemaPageResponse

/**
 * Created by koresuniku on 01.01.18.
 */

object ThreadsMapper {

    fun mapCatalogResponseToThreadListData(schemaCatalog: ThreadListJsonSchemaCatalogResponse): ThreadListData {
        val threadListData = ThreadListData()

        threadListData.setBoardName(schemaCatalog.boardName)
        threadListData.setDefaultName(schemaCatalog.defaultName)
        threadListData.setBoardList(schemaCatalog.threads)

        return threadListData
    }

    fun mapPageResponseToThreadListData(schemaCatalog: ThreadListJsonSchemaPageResponse): ThreadListData {
        val threadListData = ThreadListData()

        threadListData.setBoardName(schemaCatalog.boardName)
        threadListData.setDefaultName(schemaCatalog.defaultName)
        threadListData.setBoardList(schemaCatalog.threads)
        threadListData.setPagesCount(schemaCatalog.pages.size)

        return threadListData
    }
}