/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.litho;

import static com.facebook.litho.testing.helper.ComponentTestHelper.mountComponent;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import com.facebook.litho.testing.testrunner.ComponentsTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(ComponentsTestRunner.class)
public class DynamicPropsTest {
  private ComponentContext mContext;

  @Before
  public void setup() {
    mContext = new ComponentContext(RuntimeEnvironment.application);
  }

  @Test
  public void testDynamicAlphaApplied() {
    final float startValue = 0.8f;
    final DynamicValue<Float> alphaDV = new DynamicValue<>(startValue);

    final LithoView lithoView =
        mountComponent(
            mContext, Column.create(mContext).widthPx(80).heightPx(80).alpha(alphaDV).build());

    assertThat(lithoView.getAlpha()).isEqualTo(startValue);

    alphaDV.set(0.5f);
    assertThat(lithoView.getAlpha()).isEqualTo(0.5f);

    alphaDV.set(0.f);
    assertThat(lithoView.getAlpha()).isEqualTo(0.f);

    alphaDV.set(1.f);
    assertThat(lithoView.getAlpha()).isEqualTo(1.f);
  }

  @Test
  public void testDynamicTranslationApplied() {
    final float startValueX = 100;
    final float startValueY = -100;
    final DynamicValue<Float> translationXDV = new DynamicValue<>(startValueX);
    final DynamicValue<Float> translationYDV = new DynamicValue<>(startValueY);

    final LithoView lithoView =
        mountComponent(
            mContext,
            Column.create(mContext)
                .widthPx(80)
                .heightPx(80)
                .translationX(translationXDV)
                .translationY(translationYDV)
                .build());

    assertThat(lithoView.getTranslationX()).isEqualTo(startValueX);
    assertThat(lithoView.getTranslationY()).isEqualTo(startValueY);

    translationXDV.set(50.f);
    translationYDV.set(20.f);
    assertThat(lithoView.getTranslationX()).isEqualTo(50.f);
    assertThat(lithoView.getTranslationY()).isEqualTo(20.f);

    translationXDV.set(-50.f);
    translationYDV.set(-20.f);
    assertThat(lithoView.getTranslationX()).isEqualTo(-50.f);
    assertThat(lithoView.getTranslationY()).isEqualTo(-20.f);

    translationXDV.set(0f);
    translationYDV.set(0f);
    assertThat(lithoView.getTranslationX()).isEqualTo(0f);
    assertThat(lithoView.getTranslationY()).isEqualTo(0f);
  }

  @Test
  public void testDynamicScaleApplied() {
    final float startValueX = 1.5f;
    final float startValueY = -1.5f;
    final DynamicValue<Float> scaleXDV = new DynamicValue<>(startValueX);
    final DynamicValue<Float> scaleYDV = new DynamicValue<>(startValueY);

    final LithoView lithoView =
        mountComponent(
            mContext,
            Column.create(mContext)
                .widthPx(80)
                .heightPx(80)
                .scaleX(scaleXDV)
                .scaleY(scaleYDV)
                .build());

    assertThat(lithoView.getScaleX()).isEqualTo(startValueX);
    assertThat(lithoView.getScaleY()).isEqualTo(startValueY);

    scaleXDV.set(0.5f);
    scaleYDV.set(2.f);
    assertThat(lithoView.getScaleX()).isEqualTo(0.5f);
    assertThat(lithoView.getScaleY()).isEqualTo(2.f);

    scaleXDV.set(2.f);
    scaleYDV.set(0.5f);
    assertThat(lithoView.getScaleX()).isEqualTo(2.f);
    assertThat(lithoView.getScaleY()).isEqualTo(.5f);

    scaleXDV.set(0f);
    scaleYDV.set(0f);
    assertThat(lithoView.getScaleX()).isEqualTo(0f);
    assertThat(lithoView.getScaleY()).isEqualTo(0f);
  }

  @Test
  public void testDynamicBackgroundColorApplied() {
    final int startValue = Color.RED;
    final DynamicValue<Integer> backgroundColorDV = new DynamicValue<>(startValue);

    final LithoView lithoView =
        mountComponent(
            mContext,
            Column.create(mContext)
                .widthPx(80)
                .heightPx(80)
                .backgroundColor(backgroundColorDV)
                .build());

    assertThat(lithoView.getBackground()).isInstanceOf(ColorDrawable.class);
    assertThat(((ColorDrawable) lithoView.getBackground()).getColor()).isEqualTo(startValue);

    backgroundColorDV.set(Color.BLUE);
    assertThat(((ColorDrawable) lithoView.getBackground()).getColor()).isEqualTo(Color.BLUE);

    backgroundColorDV.set(0x88888888);
    assertThat(((ColorDrawable) lithoView.getBackground()).getColor()).isEqualTo(0x88888888);

    backgroundColorDV.set(Color.TRANSPARENT);
    assertThat(((ColorDrawable) lithoView.getBackground()).getColor()).isEqualTo(Color.TRANSPARENT);
  }

  @Test
  public void testDynamicRotationApplied() {
    final float startValue = 0f;
    final DynamicValue<Float> rotationDV = new DynamicValue<>(startValue);

    final LithoView lithoView =
        mountComponent(
            mContext,
            Column.create(mContext).widthPx(80).heightPx(80).rotation(rotationDV).build());

    assertThat(lithoView.getRotation()).isEqualTo(startValue);

    rotationDV.set(364f);
    assertThat(lithoView.getRotation()).isEqualTo(364f);

    rotationDV.set(520f);
    assertThat(lithoView.getRotation()).isEqualTo(520f);

    rotationDV.set(-1.f);
    assertThat(lithoView.getRotation()).isEqualTo(-1.f);
  }

  @Test
  @Config(sdk = Build.VERSION_CODES.LOLLIPOP)
  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public void testDynamicElevationApplied() {
    // We are using an old version of Robolectric which has a very limited implementation of
    // RenderNode which will always return an elevation of 0.
    // To get around this we can call the relevant mounting methods directly and verify the
    // correct values are applied using using a mock LithoView.

    final Component.Builder componentBuilder =
        new Component.Builder() {
          private Component component;

          {
            component =
                new Component("Test") {
                  @Override
                  public MountType getMountType() {
                    return MountType.VIEW;
                  }
                };
            super.init(mContext, -1, -1, component);
          }

          @Override
          public Component build() {
            return component;
          }

          @Override
          public Component.Builder getThis() {
            return this;
          }

          @Override
          protected void setComponent(Component component) {}
        };

    final float startValue = 1f;
    final DynamicValue<Float> elevationDV = new DynamicValue<>(startValue);
    final Component component = componentBuilder.shadowElevation(elevationDV).build();

    final LithoView mockLithoView = mock(LithoView.class);
    final DynamicPropsManager dynamicPropsManager = new DynamicPropsManager();
    dynamicPropsManager.onBindComponentToContent(component, mockLithoView);

    verify(mockLithoView).setElevation(startValue);

    elevationDV.set(50f);
    verify(mockLithoView).setElevation(50f);

    elevationDV.set(-50f);
    verify(mockLithoView).setElevation(-50f);
  }
}
