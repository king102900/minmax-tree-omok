

package kr.ac.ssu.yoobh17.Omok;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;

import kr.ac.ssu.yoobh17.Omok.framework.Screen;
import kr.ac.ssu.yoobh17.Omok.framework.impl.AndroidGame;
import kr.ac.ssu.yoobh17.Omok.screen.MainScreen;


public class MainActivity extends AndroidGame {

	@Override
	public Screen getStartScreen() {

		return new MainScreen( this );
	}

	@Override
	protected void onPause() {

		super.onPause();
		
	}

	@Override
	public void onBackPressed() {

		Builder dialog = new AlertDialog.Builder( this );

		dialog.setMessage( "게임을 종료하시겠습니까?" );
		dialog.setPositiveButton( "예", new DialogInterface.OnClickListener() {

			@Override
			public void onClick( DialogInterface dialog, int which ) {

				MainActivity.this.finish();

			}
		} );
		dialog.setNegativeButton( "아니오", null );
		dialog.show();

	}

}
