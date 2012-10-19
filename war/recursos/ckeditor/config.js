/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	config.language = 'es';
	config.toolbar = 'Basic';
	// config.uiColor = '#AADC6E';
};


CKEDITOR.editorConfig = function( config )
{
	config.language = 'es';
	
	config.toolbar = 'EditorBasico';
 
	config.toolbar_EditorBasico =
	[
	 { name: 'clipboard',	items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
	 { name: 'basicstyles',	items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
	 { name: 'paragraph',	items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
	 { name: 'links',		items : [ 'Link','Unlink','Anchor' ] },
	 { name: 'insert',		items : [ 'Table','HorizontalRule','SpecialChar','PageBreak' ] },
	 '/',
	 { name: 'styles',		items : [ 'Styles','Format','Font','FontSize' ] },
	 { name: 'colors',		items : [ 'TextColor','BGColor' ] },
	 { name: 'editing',		items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] }
	];
};