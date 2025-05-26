import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { MessageInput, MessageInputSubmitEvent, MessageList, MessageListItem } from '@vaadin/react-components';
import { format } from 'date-fns';
import { ChatService } from 'Frontend/generated/endpoints';
import { useCallback, useEffect } from 'react';

export const config: ViewConfig = {
  menu: { order: 0, icon: 'line-awesome/svg/globe-solid.svg' },
  title: 'Home',
  loginRequired: true,
};

export default function HomeView() {

  const messageListItems = useSignal<MessageListItem[]>([]);
  const announcement = useSignal('');

  const isoMinutes = 'yyyy-MM-dd HH:mm';

  useEffect(() => {
    messageListItems.value = [
      {
        text: 'Hey, how can I help you?',
        time: format(new Date(), isoMinutes),
        userName: 'Assistant',
        userColorIndex: 2,
      }
    ];
  }, []);

  function createItem(text: string, assistant = false): MessageListItem {
    return {
      text,
      time: format(new Date(), isoMinutes),
      userName: assistant ? 'Assistant' : 'User',
      userColorIndex: assistant ? 2 : 1,
    };
  }

  const handleChatSubmit = useCallback((e: MessageInputSubmitEvent) => {
    const userInput = e.detail.value;

    // Add the user message to the list
    messageListItems.value = [...messageListItems.value, createItem(userInput)];

    // Add the Assistant message to the list
    const newAssistantItem = createItem('', true);
    messageListItems.value = [...messageListItems.value, newAssistantItem];

    // Announce that AI is processing
    announcement.value = 'AI is processing the prompt';

    ChatService.chat(userInput)
      .onNext((token) => {
        // Append the token to the Assistant message
        newAssistantItem.text += token;
        // Force the MessageList to re-render
        messageListItems.value = [...messageListItems.value];
      })
      .onComplete(() => {
        // Announce that a new message is available
        announcement.value = 'New message available';
      });
  }, []);

  return (
    <div>
      {/* Live region for screen reader announcements */}
      <div aria-live="polite" className="sr-only">
        {announcement.value}
      </div>

      <MessageList items={messageListItems.value} />
      <MessageInput onSubmit={handleChatSubmit} />
    </div>
  );
}
