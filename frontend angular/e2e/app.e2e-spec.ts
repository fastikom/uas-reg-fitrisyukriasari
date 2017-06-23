import { EnilaingulPage } from './app.po';

describe('enilaingul App', () => {
  let page: EnilaingulPage;

  beforeEach(() => {
    page = new EnilaingulPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
