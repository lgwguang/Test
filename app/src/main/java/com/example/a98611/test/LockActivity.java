package com.example.a98611.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.a98611.test.gestureLock.LockPatternUtils;
import com.example.a98611.test.gestureLock.LockPatternView;

import java.util.List;

/**
 * author: lgw
 * date: on 2017/5/9.
 */

public class LockActivity extends Activity implements LockPatternView.OnPatternListener {
    private LockPatternUtils lockPatternUtils;
    private LockPatternView lockPatternView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        lockPatternUtils= new LockPatternUtils(this);

        lockPatternView = (LockPatternView) findViewById(R.id.lock_pattern);
        lockPatternView.setInStealthMode(false);
        lockPatternView.setOnPatternListener(this);
    }

    @Override
    public void onPatternStart() {

    }

    @Override
    public void onPatternCleared() {

    }

    @Override
    public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

    }

    @Override
    public void onPatternDetected(List<LockPatternView.Cell> pattern) {
        Toast.makeText(LockActivity.this, lockPatternUtils.patternToString2(pattern), Toast.LENGTH_SHORT).show();
        lockPatternView.clearPattern();
        finish();
    }

}
