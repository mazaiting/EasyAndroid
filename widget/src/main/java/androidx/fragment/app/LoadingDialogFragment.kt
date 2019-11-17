package androidx.fragment.app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.mazaiting.widget.R

/**
 * 对话框Fragment
 *
 * @author mazaiting
 * @date 2018/3/9
 */

class LoadingDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 请求无标题
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置点击外部不可关闭对话框
        dialog?.setCanceledOnTouchOutside(false)
        // 设置对话框背景透明
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        // super.show(manager, tag);
        mDismissed = false
        mShownByMe = true
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        // 这里吧原来的commit()方法换成了commitAllowingStateLoss()
        ft.commitAllowingStateLoss()
    }
}
