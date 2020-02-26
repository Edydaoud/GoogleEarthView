package com.googy.googleearthview.main.presentation.fragment

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.googy.googleearthview.R
import com.googy.googleearthview.base.presentation.extension.*
import com.googy.googleearthview.base.presentation.fragment.BaseFragment
import com.googy.googleearthview.main.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.mainImageView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException


class MainFragment : BaseFragment<MainViewModel.State, MainViewModel.Command, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_main

    override fun initUi(view: View) {
        viewModel.getImage()
        nextButton.setOnClickListener { viewModel.nextImage() }
        previousButton.setOnClickListener { viewModel.previousImage() }
        mainImageView.setOnClickListener { if (uiItemsGroup.isShown) uiItemsGroup.hide() else uiItemsGroup.show() }
    }

    override fun onViewModelCommand(command: MainViewModel.Command) = with(command) {
        when (this) {
            is MainViewModel.Command.ImageReady -> {
                mainImageView.imageCenterCrop(imageUrl, thumbUrl, primaryColor)
                imageTitleTextView.text = name
                shareButton.setOnClickListener { shareLink(shareUrl) }
                setAsButton.setOnClickListener { applyWallpaper(imageUrl) }
            }
        }
    }

    private fun applyWallpaper(url: String) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                activity?.alert(R.string.set_wallpaper_target) {
                    neutralPressed(R.string.wallpaper_target_both) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            applyWallpaper(
                                mainImageView.getBitmap(url),
                                WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM
                            )
                        }
                    }
                    positiveButton(R.string.wallpaper_target_home) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            applyWallpaper(
                                mainImageView.getBitmap(url),
                                WallpaperManager.FLAG_SYSTEM
                            )
                        }
                    }
                    negativeButton(R.string.wallpaper_target_lock) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            applyWallpaper(
                                mainImageView.getBitmap(url),
                                WallpaperManager.FLAG_LOCK
                            )
                        }
                    }
                }?.show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    val intent = Intent(Intent.ACTION_ATTACH_DATA)
                        .apply {
                            addCategory(Intent.CATEGORY_DEFAULT)
                            setDataAndType(
                                Uri.parse(mainImageView.getCachedPath(url)),
                                "image/*"
                            )
                            putExtra("mimeType", "image/*")
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                    startActivity(Intent.createChooser(intent, "Set as:"))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun applyWallpaper(bitmap: Bitmap, type: Int) {
        WallpaperManager.getInstance(activity).setBitmap(
            bitmap,
            null,
            true,
            type
        )

        val home = Intent(Intent.ACTION_MAIN)
        home.addCategory(Intent.CATEGORY_HOME)
        home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(home)
    }
}