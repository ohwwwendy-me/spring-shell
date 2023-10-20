/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.shell.docs;

import org.jline.terminal.Terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.component.message.ShellMessageBuilder;
import org.springframework.shell.component.view.TerminalUI;
import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.event.EventLoop;
import org.springframework.shell.component.view.event.KeyEvent.Key;
import org.springframework.shell.geom.HorizontalAlign;
import org.springframework.shell.geom.VerticalAlign;

class TerminalUiSnippets {

	class Sample1 {

		// tag::snippet1[]
		@Autowired
		Terminal terminal;

		void sample() {
			TerminalUI ui = new TerminalUI(terminal);
			BoxView view = new BoxView();
			view.setDrawFunction((screen, rect) -> {
				screen.writerBuilder()
					.build()
					.text("Hello World", rect, HorizontalAlign.CENTER, VerticalAlign.CENTER);
				return rect;
			});
			ui.setRoot(view, true);
			ui.run();
		}
		// end::snippet1[]
	}

	class Sample2 {

		// tag::snippet2[]
		@Autowired
		Terminal terminal;

		void sample() {
			TerminalUI ui = new TerminalUI(terminal);
			BoxView view = new BoxView();
			view.setDrawFunction((screen, rect) -> {
				screen.writerBuilder()
					.build()
					.text("Hello World", rect, HorizontalAlign.CENTER, VerticalAlign.CENTER);
				return rect;
			});
			ui.setRoot(view, false);
			ui.run();
		}
		// end::snippet2[]
	}

	class Sample3 {

		// tag::snippet3[]
		@Autowired
		Terminal terminal;

		void sample() {
			TerminalUI ui = new TerminalUI(terminal);
			BoxView view = new BoxView();
			ui.setRoot(view, true);
			EventLoop eventLoop = ui.getEventLoop();
			eventLoop.keyEvents()
				.subscribe(event -> {
					if (event.getPlainKey() == Key.q && event.hasCtrl()) {
						eventLoop.dispatch(ShellMessageBuilder.ofInterrupt());
					}
				});
			ui.run();
		}
		// end::snippet3[]
	}

}
