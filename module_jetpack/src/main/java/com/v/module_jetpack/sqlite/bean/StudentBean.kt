package com.v.module_jetpack.sqlite.bean

/**
 * ClassName: StudentBean
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.sqlite.bean
 * @date 2023/8/8 22:44
 */
class StudentBean {
    var date: String? = null
    var timestamp: Long = 0
    var name: String? = null

    class Builder {
        private val bean = StudentBean()

        private var date: String? = null
        private var timestamp: Long = 0
        private var name: String? = null

        fun setName(name: String?): Builder {
            this.name = name
            return this
        }

        fun setDate(date: String?): Builder {
            this.date = date
            return this
        }

        fun setTimeStamp(timestamp: Long): Builder {
            this.timestamp = timestamp
            return this
        }

        fun build(): StudentBean {
            bean.name = name
            bean.date = date
            bean.timestamp = timestamp

            return bean
        }
    }

    override fun toString(): String {
        return "StudentBean(date=$date, timestamp=$timestamp, name=$name)"
    }
}